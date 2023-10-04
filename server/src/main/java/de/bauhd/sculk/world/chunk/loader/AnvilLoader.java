package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.SculkChunk;
import de.bauhd.sculk.world.section.PaletteHolder;
import de.bauhd.sculk.world.section.Section;
import de.bauhd.sculk.util.CoordinateUtil;
import net.kyori.adventure.nbt.*;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static de.bauhd.sculk.world.chunk.Chunk.*;

public final class AnvilLoader extends DefaultChunkLoader {

    private static final int SECTOR_SIZE = 4096;

    private final SculkServer server;
    private final Path regionPath;
    private final Map<String, RegionFile> regionCache;

    public AnvilLoader(final SculkServer server, final ChunkGenerator generator, final Path path) {
        super(generator);
        this.server = server;
        this.regionPath = path.resolve("region");
        this.regionCache = new HashMap<>();
    }

    @Override
    public @NotNull SculkChunk loadChunk(final SculkWorld world, final int x, final int z) {
        final var fileName = "r." + CoordinateUtil.regionCoordinate(x) + "." + CoordinateUtil.regionCoordinate(z) + ".mca";
        try {
            SculkChunk chunk;
            if (!this.regionCache.containsKey(fileName)) {
                final var file = this.regionPath.resolve(fileName);
                if (Files.exists(file)) {
                    this.regionCache.put(fileName, new RegionFile(file));
                } else {
                    return super.loadChunk(world, x, z);
                }
            }
            chunk = this.regionCache.get(fileName).getChunk(this.server, world, x, z);
            if (chunk == null) {
                chunk = super.loadChunk(world, x, z);
            }
            return chunk;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class RegionFile {

        private final RandomAccessFile accessFile;

        private final int[] locations = new int[1024];
        private RegionFile(final Path path) throws IOException {
            this.accessFile = new RandomAccessFile(path.toFile(), "r");
            this.accessFile.seek(0);

            for (var i = 0; i < this.locations.length; i++) {
                final var location = this.accessFile.readInt();
                this.locations[i] = location;
            }
        }

        private SculkChunk getChunk(final SculkServer server, final SculkWorld world, final int chunkX, final int chunkZ) throws IOException {
            final var offset = this.sectorOffset(this.locations[(chunkX & 31) + (chunkZ & 31) * 32]) * SECTOR_SIZE;
            var buf = ByteBuffer.allocate(5);
            this.accessFile.getChannel().read(buf, offset);
            final var length = buf.getInt(0);
            if (length < 0) return null;
            final var compressionScheme = buf.get(4);
            if (compressionScheme < 1) return null;
            buf = ByteBuffer.allocate(length);
            this.accessFile.getChannel().read(buf, offset + 5);
            buf.flip();

            final var nbt = BinaryTagIO.reader(Integer.MAX_VALUE)
                    .read(new ByteArrayInputStream(buf.array(), buf.position(), length - buf.position()),
                            switch (compressionScheme) {
                                case 1 -> BinaryTagIO.Compression.GZIP;
                                case 2 -> BinaryTagIO.Compression.ZLIB;
                                case 3 -> BinaryTagIO.Compression.NONE;
                                default ->
                                        throw new IllegalStateException("Unexpected compression scheme: " + compressionScheme);
                            });

            final var sectionList = nbt.getList("sections");
            final var sections = new Section[sectionList.size()];
            for (var i = 0; i < sectionList.size(); i++) {
                final var compound = (CompoundBinaryTag) sectionList.get(i);
                final var states = compound.getCompound("block_states");
                final var blockPalette = states.getList("palette");
                if (blockPalette.equals(ListBinaryTag.empty())) continue;
                final var section = new Section(compound.getByteArray("SkyLight"), compound.getByteArray("BlockLight"));

                loadBlocks(section, blockPalette, states);
                loadBiomes(server, chunkX, chunkZ, section, i, compound.getCompound("biomes"));

                sections[i] = section;
            }
            final var chunk = new SculkChunk(world, chunkX, chunkZ, sections, nbt.getCompound("Heightmaps"));
            for (final var blockEntity : nbt.getList("block_entities")) {
                loadBlockEntity(chunk, (CompoundBinaryTag) blockEntity);
            }
            return chunk;
        }

        private long sectorOffset(final int location) {
            return location >>> 8;
        }

    }
    public static void loadBlocks(
            final Section section,
            final ListBinaryTag blockPalette,
            final CompoundBinaryTag states
    ) {
        final var blocks = (PaletteHolder) section.blocks();
        final var palette = new int[blockPalette.size()];
        for (var k = 0; k < palette.length; k++) {
            final var entry = blockPalette.getCompound(k);
            final var block = entry.getString("Name");
            final var properties = entry.getCompound("Properties");
            if (!properties.equals(CompoundBinaryTag.empty())) {
                final var propertiesCompound = entry.getCompound("Properties");
                final var map = new HashMap<String, String>(propertiesCompound.keySet().size()); // TODO: change if #size is available
                for (final var property : propertiesCompound) {
                    map.put(property.getKey(), ((StringBinaryTag) property.getValue()).value());
                }
                palette[k] = Block.get(block).properties(map).getId();
            } else {
                palette[k] = Block.get(block).getId();
            }
        }
        if (palette.length == 1) {
            blocks.fill(palette[0]);
            return;
        }
        blocks.setIndirectPalette();
        final var blockStates = uncompressedBlockStates(states);
        for (var y = 0; y < CHUNK_SECTION_SIZE; y++) {
            for (var z = 0; z < CHUNK_SECTION_SIZE; z++) {
                for (var x = 0; x < CHUNK_SECTION_SIZE; x++) {
                    final var blockIndex = y * CHUNK_SECTION_SIZE * CHUNK_SECTION_SIZE + z * CHUNK_SECTION_SIZE + x;
                    final var paletteIndex = blockStates[blockIndex];
                    blocks.set(x, y, z, palette[paletteIndex]);
                }
            }
        }
    }

    public static void loadBiomes(
            final SculkServer server,
            final int chunkX,
            final int chunkZ,
            final Section section,
            final int id,
            final CompoundBinaryTag biomeData
    ) {
        final var biomes = (PaletteHolder) section.biomes();
        final var biomePalette = biomeData.getList("palette");
        final var palette = new int[biomePalette.size()];
        for (var k = 0; k < palette.length; k++) {
            final var entry = biomePalette.getCompound(k);
            palette[k] = server.getBiomeRegistry().get(entry.getString("value")).id();
        }
        if (palette.length == 1) {
            biomes.fill(palette[0]);
            return;
        }
        biomes.setIndirectPalette();
        final var biomeIndexes = uncompressedBiomeIndexes(biomeData, biomePalette.size());
        for (var y = 0; y < CHUNK_SECTION_SIZE; y++) {
            for (var z = 0; z < CHUNK_SIZE_Z; z++) {
                for (var x = 0; x < CHUNK_SIZE_X; x++) {
                    final var finalX = (chunkX * CHUNK_SIZE_X + x);
                    final var finalZ = (chunkZ * CHUNK_SIZE_Z + z);
                    final var finalY = (id * CHUNK_SECTION_SIZE + y);
                    final var index = x / 4 + (z / 4) * 4 + (y / 4) * 16;
                    biomes.set(finalX, finalY, finalZ, biomeIndexes[index]);
                }
            }
        }
    }

    public static void loadBlockEntity(SculkChunk chunk, CompoundBinaryTag compound) {
        final var x = compound.getInt("x");
        final var y = compound.getInt("y");
        final var z = compound.getInt("z");
        final var entity = (Block.Entity<?>) chunk.getBlock(x, y, z);
        chunk.setBlock(x, y, z, entity.nbt(compound.remove("id")
                .remove("x").remove("y").remove("z").remove("keepPacked")));
    }

    private static int[] uncompressedBlockStates(CompoundBinaryTag states) {
        final var longs = states.getLongArray("data");
        final var sizeInBits = longs.length * 64 / 4096;
        var expectedCompressedLength = 0;
        if (longs.length == 0) {
            expectedCompressedLength = -1;
        } else {
            final var intPerLong = 64 / sizeInBits;
            expectedCompressedLength = (int) Math.ceil(4096.0 / intPerLong);
        }
        if (longs.length != expectedCompressedLength) {
            if (longs.length == 0) {
                return new int[4096];
            }
        }
        return uncompress(longs, sizeInBits);
    }

    private static int[] uncompressedBiomeIndexes(CompoundBinaryTag biomes, final double size) {
        final var compressedBiomes = biomes.getLongArray("data");
        final var sizeInBits = (int) Math.ceil(Math.log(size) / Math.log(2));
        return uncompress(compressedBiomes, sizeInBits);
    }

    private static int[] uncompress(final long[] longs, final int sizeInBits) {
        final var intPerLong = Math.floor(64.0 / sizeInBits);
        final var intCount = (int) Math.ceil(longs.length * intPerLong);
        final var ints = new int[intCount];
        final var intPerLongCeil = (int) Math.ceil(intPerLong);
        final var mask = (1L << sizeInBits) - 1L;
        for (var i = 0; i < intCount; i++) {
            final var longIndex = i / intPerLongCeil;
            final var subIndex = i % intPerLongCeil;
            final var value = (int) ((longs[longIndex] >> (subIndex * sizeInBits)) & mask);
            ints[i] = value;
        }
        return ints;
    }
}
