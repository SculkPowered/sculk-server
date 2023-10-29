package io.github.sculkpowered.server.world;

import com.github.luben.zstd.Zstd;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import io.github.sculkpowered.server.world.chunk.loader.AnvilLoader;
import io.github.sculkpowered.server.world.section.Section;
import io.github.sculkpowered.server.util.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
  private static final byte VERSION = 0x0A;
  private static final Logger LOGGER = LogManager.getLogger(SlimeFormat.class);

  // LOADING

  public static void load(
      final SculkServer server,
      final SculkWorld world,
      final WorldLoader.Slime loader
  ) {
    try (final var inputStream = new DataInputStream(new ByteArrayInputStream(loader.bytes()))) {
      if (inputStream.readShort() != HEADER) {
        throw new AssertionError();
      }
      if (inputStream.readUnsignedByte() != VERSION) {
        throw new UnsupportedOperationException("Currently only slime version 10 is supported!");
      }
      inputStream.readInt();
      readChunks(server, world, readCompressed(inputStream));

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
      readCompressed(inputStream);
    } catch (IOException e) {
      LOGGER.error("Couldn't load slime world", e);
    }
  }

  private static void readChunks(final SculkServer server, final SculkWorld world,
      final byte[] bytes) {
    try (final var inputStream = new DataInputStream(new ByteArrayInputStream(bytes))) {
      final var chunks = inputStream.readInt();
      for (var i = 0; i < chunks; i++) {
        final var x = inputStream.readInt();
        final var z = inputStream.readInt();

        final var heightmaps = readCompound(inputStream);

        final var sections = new Section[inputStream.readInt()];
        for (var j = 0; j < sections.length; j++) {
          byte[] blockLight = Constants.EMPTY_BYTE_ARRAY;
          if (inputStream.readBoolean()) {
            blockLight = new byte[2048];
            if (inputStream.read(blockLight) != 2048) {
              throw new AssertionError();
            }
          }
          byte[] skyLight = Constants.EMPTY_BYTE_ARRAY;
          if (inputStream.readBoolean()) {
            skyLight = new byte[2048];
            if (inputStream.read(skyLight) != 2048) {
              throw new AssertionError();
            }
          }

          final var section = new Section(blockLight, skyLight);

          final var blockData = readCompound(inputStream);
          AnvilLoader.loadBlocks(section, blockData.getList("palette"), blockData);
          AnvilLoader.loadBiomes(server, section, readCompound(inputStream));

          sections[j] = section;
        }
        world.putChunk(new SculkChunk(world, x, z, sections, heightmaps));
      }
    } catch (IOException e) {
      LOGGER.error("Couldn't read slime chunks", e);
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

        world.spawnEntity(entity, new Position(pos.getDouble(0), pos.getDouble(1), pos.getDouble(2),
            rotation.getFloat(0), rotation.getFloat(1)));
      } catch (ClassNotFoundException e) {
        LOGGER.error("Couldn't find entity " + id, e);
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
      final OutputStream outputStream) {
    try (final var dataOutput = new DataOutputStream(outputStream)) {
      dataOutput.writeShort(HEADER);
      dataOutput.writeByte(VERSION);
      dataOutput.writeInt(3465);

      final var chunks = world.chunks().values();

      writeChunks(server, chunks, dataOutput);

      final var tiles = ListBinaryTag.builder();
      final var entities = ListBinaryTag.builder();
      for (final var chunk : chunks) {
        for (final var entry : chunk.blockEntities().entrySet()) {
          final var point = entry.getKey();
          tiles.add(CompoundBinaryTag.builder()
              .putInt("x", point.x())
              .putInt("y", point.y())
              .putInt("z", point.z())
              .putByte("keepPacked", (byte) 0)
              .put(entry.getValue().nbt())
              .build());
        }
        for (final var entity : chunk.entities()) {
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
      writeCompoundCompressed(dataOutput, CompoundBinaryTag.empty());
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
          if (section.skyLight() != Constants.EMPTY_BYTE_ARRAY) {
            outputSteam.writeBoolean(true);
            outputSteam.write(section.skyLight());
          } else {
            outputSteam.writeBoolean(false);
          }
          if (section.blockLight() != Constants.EMPTY_BYTE_ARRAY) {
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
