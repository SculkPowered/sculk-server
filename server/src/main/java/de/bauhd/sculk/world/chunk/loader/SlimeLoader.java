package de.bauhd.sculk.world.chunk.loader;

import com.github.luben.zstd.Zstd;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.chunk.SculkChunk;
import de.bauhd.sculk.world.section.Section;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class SlimeLoader {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public static void load(final SculkServer server, final SculkWorld world, final byte[] bytes) {
        try (final var inputStream = new DataInputStream(new ByteArrayInputStream(bytes))) {
            if (inputStream.readShort() != (short) 0xB10B) throw new AssertionError();
            if (inputStream.readUnsignedByte() != 0x0A)
                throw new UnsupportedOperationException("Currently only slime version 10 is supported!");
            inputStream.readInt();
            readChunks(server, world, readCompressed(inputStream));

            final var tileEntities = readCompound(readCompressed(inputStream));
            for (final var tiles : tileEntities.getList("tiles")) {
                final var tilesCompound = (CompoundBinaryTag) tiles;
                AnvilLoader.loadBlockEntity(world.getChunkAt(tilesCompound.getInt("x"), tilesCompound.getInt("z")), tilesCompound);
            }
            final var entities = readCompressed(inputStream);
            final var extra = readCompressed(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
}
