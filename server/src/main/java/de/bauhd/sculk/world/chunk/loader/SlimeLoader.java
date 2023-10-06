package de.bauhd.sculk.world.chunk.loader;

import com.github.luben.zstd.Zstd;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.WorldLoader;
import de.bauhd.sculk.world.chunk.SculkChunk;
import de.bauhd.sculk.world.section.Section;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static de.bauhd.sculk.util.Constants.EMPTY_BYTE_ARRAY;

public final class SlimeLoader {

    private static final Logger LOGGER = LogManager.getLogger(SlimeLoader.class);

    public static void load(final SculkServer server, final SculkWorld world, final WorldLoader.Slime loader) {
        try (final var inputStream = new DataInputStream(new ByteArrayInputStream(loader.bytes()))) {
            if (inputStream.readShort() != (short) 0xB10B) throw new AssertionError();
            if (inputStream.readUnsignedByte() != 0x0A)
                throw new UnsupportedOperationException("Currently only slime version 10 is supported!");
            inputStream.readInt();
            readChunks(server, world, readCompressed(inputStream));

            final var tileEntityData = readCompressed(inputStream);
            if (loader.blockEntities()) {
                final var tileEntities = readCompound(tileEntityData);
                for (final var tiles : tileEntities.getList("tiles")) {
                    final var tilesCompound = (CompoundBinaryTag) tiles;
                    AnvilLoader.loadBlockEntity(world
                            .getChunkAt(tilesCompound.getInt("x"), tilesCompound.getInt("z")), tilesCompound);
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

    private static void readChunks(final SculkServer server, final SculkWorld world, final byte[] bytes) {
        try (final var inputStream = new DataInputStream(new ByteArrayInputStream(bytes))) {
            final var chunks = inputStream.readInt();
            for (var i = 0; i < chunks; i++) {
                final var x = inputStream.readInt();
                final var z = inputStream.readInt();

                final var heightmaps = readCompound(inputStream);

                final var sections = new Section[inputStream.readInt()];
                for (var j = 0; j < sections.length; j++) {
                    byte[] blockLight = EMPTY_BYTE_ARRAY;
                    if (inputStream.readBoolean()) {
                        blockLight = new byte[2048];
                        if (inputStream.read(blockLight) != 2048) throw new AssertionError();
                    }
                    byte[] skyLight = EMPTY_BYTE_ARRAY;
                    if (inputStream.readBoolean()) {
                        skyLight = new byte[2048];
                        if (inputStream.read(skyLight) != 2048) throw new AssertionError();
                    }

                    final var section = new Section(blockLight, skyLight);

                    final var blockData = readCompound(inputStream);
                    AnvilLoader.loadBlocks(section, blockData.getList("palette"), blockData);
                    AnvilLoader.loadBiomes(server, x, z, section, j, readCompound(inputStream));

                    sections[j] = section;
                }
                world.putChunk(new SculkChunk(world, x, z, sections, heightmaps));
            }
        } catch (IOException e) {
            LOGGER.error("Couldn't read slime chunks", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadEntities(SculkServer server, SculkWorld world, CompoundBinaryTag entities) {
        for (final var entityTag : entities.getList("entities")) {
            final var entityCompound = (CompoundBinaryTag) entityTag;
            final var id = entityCompound.getString("id");
            try {
                final var clazz = (Class<? extends Entity>) Class.forName("de.bauhd.sculk.entity." + keyToName(id));
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
        if (inputStream.read(compressed) != compressedLength) throw new AssertionError();
        Zstd.decompress(uncompressed, compressed);
        return uncompressed;
    }

    private static CompoundBinaryTag readCompound(final DataInputStream inputStream) throws IOException {
        final var data = new byte[inputStream.readInt()];
        if (inputStream.read(data) != data.length) throw new AssertionError();
        return readCompound(data);
    }

    private static CompoundBinaryTag readCompound(final byte[] data) throws IOException {
        return BinaryTagIO.reader().read(new ByteArrayInputStream(data));
    }

    private static String keyToName(final String key) {
        final var stringBuilder = new StringBuilder();
        for (final var s : key.substring(key.indexOf(":") + 1).split("_")) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
