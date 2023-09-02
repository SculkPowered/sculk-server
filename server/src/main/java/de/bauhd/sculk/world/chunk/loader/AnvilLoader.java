package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.world.MinecraftWorld;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.MinecraftChunk;
import de.bauhd.sculk.world.section.PaletteHolder;
import de.bauhd.sculk.world.section.Section;
import de.bauhd.sculk.util.CoordinateUtil;
import de.bauhd.sculk.world.chunk.Chunk;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class AnvilLoader extends DefaultChunkLoader {

    private static final int SECTOR_SIZE = 4096;

    private final SculkMinecraftServer server;
    private final Path regionPath;
    private final Map<String, RegionFile> regionCache;

    public AnvilLoader(final SculkMinecraftServer server, final ChunkGenerator generator, final Path path) {
        super(generator);
        this.server = server;
        this.regionPath = path.resolve("region");
        this.regionCache = new HashMap<>();
    }

    @Override
    public @NotNull MinecraftChunk loadChunk(final MinecraftWorld world, final int x, final int z) {
        final var fileName = "r." + CoordinateUtil.regionCoordinate(x) + "." + CoordinateUtil.regionCoordinate(z) + ".mca";
        try {
            MinecraftChunk chunk;
            if (!this.regionCache.containsKey(fileName)) {
                final var file = this.regionPath.resolve(fileName);
                if (Files.exists(file)) {
                    this.regionCache.put(fileName, new RegionFile(file));
                } else {
                    return super.loadChunk(world, x, z);
                }
            }
            chunk = this.regionCache.get(fileName).getChunk(world, x, z);
            if (chunk == null) {
                chunk = super.loadChunk(world, x, z);
            }
            return chunk;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final class RegionFile {

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

        private MinecraftChunk getChunk(final MinecraftWorld world, final int chunkX, final int chunkZ) throws IOException {
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

                // load blocks
                {
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
                    } else {
                        blocks.setIndirectPalette();
                        final var blockStates = this.uncompressedBlockStates(states);
                        for (var y = 0; y < Chunk.CHUNK_SECTION_SIZE; y++) {
                            for (var z = 0; z < Chunk.CHUNK_SECTION_SIZE; z++) {
                                for (var x = 0; x < Chunk.CHUNK_SECTION_SIZE; x++) {
                                    final var blockIndex = y * Chunk.CHUNK_SECTION_SIZE * Chunk.CHUNK_SECTION_SIZE + z * Chunk.CHUNK_SECTION_SIZE + x;
                                    final var paletteIndex = blockStates[blockIndex];
                                    blocks.set(x, y, z, palette[paletteIndex]);
                                }
                            }
                        }
                    }
                }

                // load biomes
                {
                    final var biomes = (PaletteHolder) section.biomes();
                    final var biomesCompound = compound.getCompound("biomes");
                    final var biomePalette = biomesCompound.getList("palette");
                    final var palette = new int[biomePalette.size()];
                    for (var k = 0; k < palette.length; k++) {
                        final var entry = biomePalette.getCompound(k);
                        palette[k] = AnvilLoader.this.server.getBiomeRegistry().get(entry.getString("value")).id();
                    }
                    if (palette.length == 1) {
                        biomes.fill(palette[0]);
                    } else {
                        biomes.setIndirectPalette();
                        final var biomeIndexes = this.uncompressedBiomeIndexes(biomesCompound, biomePalette.size());
                        for (var y = 0; y < Chunk.CHUNK_SECTION_SIZE; y++) {
                            for (var z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                                for (var x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
                                    final var finalX = (chunkX * Chunk.CHUNK_SIZE_X + x);
                                    final var finalZ = (chunkZ * Chunk.CHUNK_SIZE_Z + z);
                                    final var finalY = (i * Chunk.CHUNK_SECTION_SIZE + y);
                                    final var index = x / 4 + (z / 4) * 4 + (y / 4) * 16;
                                    biomes.set(finalX, finalY, finalZ, biomeIndexes[index]);
                                }
                            }
                        }
                    }
                }

                sections[i] = section;
            }
            return new MinecraftChunk(world, chunkX, chunkZ, sections);
        }

        private int[] uncompressedBlockStates(CompoundBinaryTag states) {
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
            return this.uncompress(longs, sizeInBits);
        }

        private int[] uncompressedBiomeIndexes(CompoundBinaryTag biomes, final double size) {
            final var compressedBiomes = biomes.getLongArray("data");
            final var sizeInBits = (int) Math.ceil(Math.log(size) / Math.log(2));
            return this.uncompress(compressedBiomes, sizeInBits);
        }

        private int[] uncompress(final long[] longs, final int sizeInBits) {
            final var intPerLong = Math.floor(64.0 / sizeInBits);
            final var intCount = (int) Math.ceil(longs.length * intPerLong);
            final var ints = new int[intCount];
            final var intPerLongCeil = (int) Math.ceil(intPerLong);
            final var mask = (1L << sizeInBits) - 1L;
            for (int i = 0; i < intCount; i++) {
                final var longIndex = i / intPerLongCeil;
                final var subIndex = i % intPerLongCeil;
                final var value = (int) ((longs[longIndex] >> (subIndex * sizeInBits)) & mask);
                ints[i] = value;
            }
            return ints;
        }

        private long sectorOffset(final int location) {
            return location >>> 8;
        }
    }
}
