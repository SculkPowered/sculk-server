package io.github.sculkpowered.server.world;

import static io.github.sculkpowered.server.util.Constants.EMPTY_BYTE_ARRAY;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionXFromBlockIndex;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionYFromBlockIndex;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionZFromBlockIndex;

import com.github.luben.zstd.Zstd;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import io.github.sculkpowered.server.world.chunk.loader.AnvilLoader;
import io.github.sculkpowered.server.world.section.Section;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SlimeFormat {

  private static final short HEADER = (short) 0xB10B;
  private static final byte VERSION_10 = 0x0A;
  private static final byte VERSION_11 = 0x0B;
  private static final int LIGHT_SIZE = 2048;
  private static final Logger LOGGER = LogManager.getLogger(SlimeFormat.class);

  // LOADING

  public static void load(
      final SculkServer server,
      final SculkWorld world,
      final WorldLoader.Slime loader
  ) {
    try (final var inputStream = loader.inputStream()) {
      if (inputStream.readShort() != HEADER) {
        throw new AssertionError();
      }
      final var version = inputStream.readUnsignedByte();
      inputStream.readInt(); // world version
      switch (version) {
        case VERSION_10 -> {
          readChunks10(server, world, readCompressed(inputStream));
          readEntities(server, world, loader, inputStream);
        }
        case VERSION_11 -> readChunks11(server, world, loader, readCompressed(inputStream));
        default -> throw new UnsupportedOperationException("Slime version " + version + " not supported!");
      }

      world.extraData(readCompound(readCompressed(inputStream)));
    } catch (IOException e) {
      LOGGER.error("Couldn't load slime world", e);
    }
  }

  private static void readChunks10(final SculkServer server, final SculkWorld world,
      final byte[] bytes) {
    try (final var inputStream = new DataInputStream(new ByteArrayInputStream(bytes))) {
      final var chunks = inputStream.readInt();
      for (var i = 0; i < chunks; i++) {
        final var x = inputStream.readInt();
        final var z = inputStream.readInt();

        final var heightmaps = readCompound(inputStream);
        final var sections = readSections(server, inputStream);
        world.putChunk(new SculkChunk(world, x, z, sections, heightmaps));
      }
    } catch (IOException e) {
      LOGGER.error("Couldn't read slime chunks", e);
    }
  }

  private static void readChunks11(final SculkServer server, final SculkWorld world,
      final WorldLoader.Slime loader, final byte[] bytes) {
    try (final var inputStream = new DataInputStream(new ByteArrayInputStream(bytes))) {
      final var chunks = inputStream.readInt();
      for (var i = 0; i < chunks; i++) {
        final var x = inputStream.readInt();
        final var z = inputStream.readInt();

        final var sections = readSections(server, inputStream);
        final var heightmaps = readCompound(inputStream);
        readEntities(server, world, loader, inputStream);
        world.putChunk(new SculkChunk(world, x, z, sections, heightmaps));
      }
    } catch (IOException e) {
      LOGGER.error("Couldn't read slime chunks", e);
    }
  }

  private static Section[] readSections(final SculkServer server, final DataInputStream inputStream)
      throws IOException {
    final var sections = new Section[inputStream.readInt()];
    for (var j = 0; j < sections.length; j++) {
      var blockLight = EMPTY_BYTE_ARRAY;
      if (inputStream.readBoolean()) {
        blockLight = new byte[LIGHT_SIZE];
        if (inputStream.read(blockLight) != LIGHT_SIZE) {
          throw new AssertionError();
        }
      }
      var skyLight = EMPTY_BYTE_ARRAY;
      if (inputStream.readBoolean()) {
        skyLight = new byte[LIGHT_SIZE];
        if (inputStream.read(skyLight) != LIGHT_SIZE) {
          throw new AssertionError();
        }
      }
      final var section = new Section(blockLight, skyLight);
      final var blockData = readCompound(inputStream);
      AnvilLoader.loadBlocks(section, blockData.getList("palette"), blockData);
      AnvilLoader.loadBiomes(server, section, readCompound(inputStream));

      sections[j] = section;
    }
    return sections;
  }

  private static void readEntities(final SculkServer server, final SculkWorld world,
      final WorldLoader.Slime loader, final DataInputStream inputStream) throws IOException {
    final var tileEntityData = readCompressed(inputStream);
    if (loader.blockEntities()) {
      final var tileEntities = readCompound(tileEntityData);
      for (final var tiles : tileEntities.getList("tiles")) {
        final var tilesCompound = (CompoundBinaryTag) tiles;
        AnvilLoader.loadBlockEntity(world
            .chunkAt(tilesCompound.getInt("x"), tilesCompound.getInt("z")), tilesCompound);
      }
    }

    final var entityData = readCompressed(inputStream);
    if (loader.entities()) {
      loadEntities(server, world, readCompound(entityData));
    }
  }

  @SuppressWarnings("unchecked")
  private static void loadEntities(SculkServer server, SculkWorld world,
      CompoundBinaryTag entities) {
    for (final var entityTag : entities.getList("entities")) {
      final var entityCompound = (CompoundBinaryTag) entityTag;
      final var id = entityCompound.getString("id");
      try {
        final var clazz = (Class<? extends Entity>) Class.forName(
            "io.github.sculkpowered.server.entity." + keyToName(id));
        final var entity = server.createEntity(clazz);
        final var pos = entityCompound.getList("Pos");
        final var rotation = entityCompound.getList("Rotation");

        world.spawnEntity(entity,
            Position.position(pos.getDouble(0), pos.getDouble(1), pos.getDouble(2),
                rotation.getFloat(0), rotation.getFloat(1)));
      } catch (ClassNotFoundException e) {
        LOGGER.error("Couldn't find entity {}", id, e);
      }
    }
  }

  private static byte[] readCompressed(final DataInputStream inputStream) throws IOException {
    final var compressedLength = inputStream.readInt();
    final var uncompressedLength = inputStream.readInt();
    final var compressed = new byte[compressedLength];
    final var uncompressed = new byte[uncompressedLength];
    if (inputStream.read(compressed) != compressedLength) {
      throw new AssertionError();
    }
    Zstd.decompress(uncompressed, compressed);
    return uncompressed;
  }

  private static CompoundBinaryTag readCompound(final DataInputStream inputStream)
      throws IOException {
    final var data = new byte[inputStream.readInt()];
    if (inputStream.read(data) != data.length) {
      throw new AssertionError();
    }
    return readCompound(data);
  }

  private static CompoundBinaryTag readCompound(final byte[] data) throws IOException {
    return BinaryTagIO.reader().read(new ByteArrayInputStream(data));
  }

  // SAVING

  public static void save(final SculkServer server, final SculkWorld world,
      final DataOutputStream outputStream) {
    try (final var dataOutput = outputStream) {
      dataOutput.writeShort(HEADER);
      dataOutput.writeByte(VERSION_10);
      dataOutput.writeInt(3465);

      final var chunks = world.chunks().values();

      writeChunks(server, chunks, dataOutput);

      final var tiles = ListBinaryTag.builder();
      final var entities = ListBinaryTag.builder();
      for (final var chunk : chunks) {
        for (final var entry : chunk.blockEntries().int2ObjectEntrySet()) {
          final var point = entry.getIntKey();
          tiles.add(CompoundBinaryTag.builder()
              .putInt("x", chunkPositionXFromBlockIndex(point))
              .putInt("y", chunkPositionYFromBlockIndex(point))
              .putInt("z", chunkPositionZFromBlockIndex(point))
              .putByte("keepPacked", (byte) 0)
              .put(entry.getValue().nbt())
              .build());
        }
        for (final var entity : chunk.entities()) {
          if (entity instanceof Player) {
            continue; // do not save players!
          }

          final var position = entity.position();
          entities.add(CompoundBinaryTag.builder()
              .putString("id", entity.type().key())
              .put("Pos", ListBinaryTag.builder()
                  .add(DoubleBinaryTag.doubleBinaryTag(position.x()))
                  .add(DoubleBinaryTag.doubleBinaryTag(position.y()))
                  .add(DoubleBinaryTag.doubleBinaryTag(position.z()))
                  .build())
              .put("Rotation", ListBinaryTag.builder()
                  .add(FloatBinaryTag.floatBinaryTag(position.yaw()))
                  .add(FloatBinaryTag.floatBinaryTag(position.pitch()))
                  .build())
              .build());
        }
      }

      writeCompoundCompressed(dataOutput,
          CompoundBinaryTag.builder().put("tiles", tiles.build()).build());
      writeCompoundCompressed(dataOutput,
          CompoundBinaryTag.builder().put("entities", entities.build()).build());
      writeCompoundCompressed(dataOutput, world.extraData());
    } catch (IOException e) {
      LOGGER.error("Error while saving world", e);
    }
  }

  private static void writeChunks(final SculkServer server, final Collection<SculkChunk> chunks,
      final DataOutputStream dataOutput) throws IOException {
    try (final var byteStream = new ByteArrayOutputStream();
        final var outputSteam = new DataOutputStream(byteStream)) {
      outputSteam.writeInt(chunks.size());
      for (final var chunk : chunks) {
        outputSteam.writeInt(chunk.x());
        outputSteam.writeInt(chunk.z());

        writeCompound(outputSteam, chunk.heightmaps());

        final var sections = chunk.sections();
        outputSteam.writeInt(sections.length);
        for (final var section : sections) {
          if (section.skyLight() != EMPTY_BYTE_ARRAY) {
            outputSteam.writeBoolean(true);
            outputSteam.write(section.skyLight());
          } else {
            outputSteam.writeBoolean(false);
          }
          if (section.blockLight() != EMPTY_BYTE_ARRAY) {
            outputSteam.writeBoolean(true);
            outputSteam.write(section.blockLight());
          } else {
            outputSteam.writeBoolean(false);
          }

          writeCompound(outputSteam, AnvilLoader.blockStatesToNbt(section.blocks()));
          writeCompound(outputSteam, AnvilLoader.biomesToNbt(server, section.biomes()));
        }
      }
      writeCompressed(dataOutput, byteStream.toByteArray());
    }
  }

  private static void writeCompressed(final DataOutputStream outputStream, final byte[] bytes)
      throws IOException {
    final var compressed = Zstd.compress(bytes);
    outputStream.writeInt(compressed.length);
    outputStream.writeInt(bytes.length);
    outputStream.write(compressed);
  }

  private static void writeCompound(final DataOutputStream outputStream,
      final CompoundBinaryTag compound) throws IOException {
    try (final var byteStream = new ByteArrayOutputStream()) {
      BinaryTagIO.writer().write(compound, byteStream);
      final var bytes = byteStream.toByteArray();
      outputStream.writeInt(bytes.length);
      outputStream.write(bytes);
    }
  }

  private static void writeCompoundCompressed(final DataOutputStream outputStream,
      final CompoundBinaryTag compound) throws IOException {
    try (final var byteStream = new ByteArrayOutputStream();
        final var dataOutput = new DataOutputStream(byteStream)) {
      BinaryTagIO.writer().write(compound, (DataOutput) dataOutput);
      writeCompressed(outputStream, byteStream.toByteArray());
    }
  }

  private static String keyToName(final String key) {
    final var stringBuilder = new StringBuilder();
    for (final var s : key.substring(key.indexOf(":") + 1).split("_")) {
      stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
    }
    return stringBuilder.toString();
  }
}
