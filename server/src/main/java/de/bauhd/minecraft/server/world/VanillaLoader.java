package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.world.chunk.Chunk;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static de.bauhd.minecraft.server.world.chunk.Chunk.CHUNK_SECTION_SIZE;

public final class VanillaLoader {

    private static final int SECTOR_SIZE = 4096;

    private final AdvancedMinecraftServer server;
    private final Path regionPath;
    private final Map<String, RegionFile> regionCache;
    private World world;

    public VanillaLoader(final AdvancedMinecraftServer server, final Path path) {
        this.server = server;
        this.regionPath = path.resolve("region");
        this.regionCache = new HashMap<>();
    }

    public MinecraftChunk getChunk(final int x, final int z) {
        final var fileName = "r." + this.toRegionCoordinate(x) +
                "." + this.toRegionCoordinate(z) + ".mca";
        if (!this.regionCache.containsKey(fileName)) {
            try {
                this.regionCache.put(fileName, new RegionFile(this.regionPath.resolve(fileName)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return this.regionCache.get(fileName).getChunk(x, z);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    private int toRegionCoordinate(final int coordinate) {
        return (int) Math.floor((double) coordinate / 32);
    }

    private long chunkIndex(final int x, final int z) {
        return ((((long) x) << 32) | (z & 0xFFFFFFFFL));
    }

    private final class RegionFile {

        private final Long2ObjectMap<MinecraftChunk> chunks;
        private final RandomAccessFile accessFile;
        private final int[] locations = new int[1024];
        private final boolean[] freeSectors;

        public RegionFile(final Path path) throws IOException {
            this.accessFile = new RandomAccessFile(path.toFile(), "r");
            final var available = this.accessFile.length() / SECTOR_SIZE;

            this.freeSectors = new boolean[(int) available];
            for (int i = 0; i < this.freeSectors.length; i++) {
                this.freeSectors[i] = i != 0 && i != 1;
            }
            this.accessFile.seek(0);

            for (int i = 0; i < this.locations.length; i++) {
                final var location = this.accessFile.readInt();
                this.locations[i] = location;

                final var inSectors = this.sizeInSectors(location);
                if (location != 0 && this.sectorOffset(location) + inSectors <= this.freeSectors.length) {
                    for (int j = 0; j < inSectors; j++) {
                        this.freeSectors[(int) (j + sectorOffset(location))] = false;
                    }
                }
            }

            this.chunks = new Long2ObjectOpenHashMap<>();
            /*try (final var channel = FileChannel.open(path)) {
                final var size = channel.size();
                final var totalSectors = size >>> 12 + (-(size & 4095) >>> 63);
                for (long sector = 2, maxSector = Math.min(Integer.MAX_VALUE >>> 8, totalSectors); sector < maxSector; ++sector) {
                    var buf = ByteBuffer.allocate(4);
                    channel.read(buf, sector * 4096L);
                    final var length = buf.getInt(0);
                    if (length < 0) continue;
                    final var offset = sector * 4096L + 4L;
                    if ((offset + length) > size) continue;
                    buf = ByteBuffer.allocate(length);
                    channel.read(buf, offset);
                    buf.flip();
                    final var compressionScheme = buf.get();
                    if (compressionScheme < 0) continue;

                    final BinaryTagIO.Compression compression = switch (compressionScheme) {
                        case 1 -> BinaryTagIO.Compression.GZIP;
                        case 2 -> BinaryTagIO.Compression.ZLIB;
                        case 3 -> BinaryTagIO.Compression.NONE;
                        default ->
                                throw new IllegalStateException("Unexpected compression scheme: " + compressionScheme);
                    };

                    final var nbt = BinaryTagIO.reader(Integer.MAX_VALUE)
                            .read(new ByteArrayInputStream(buf.array(), buf.position(), length - buf.position()), compression);


                }
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }*/
        }

        private MinecraftChunk getChunk(final int chunkX, final int chunkZ) throws IOException {
            final var index = VanillaLoader.this.chunkIndex(chunkX, chunkZ);
            var chunk = this.chunks.get(index);
            if (chunk != null) {
                return chunk;
            }

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

            chunk = new MinecraftChunk(VanillaLoader.this.server, VanillaLoader.this.world, chunkX, chunkZ);

            synchronized (chunk) {
                for (final var section : nbt.getList("sections")) {
                    final var compound = ((CompoundBinaryTag) section);
                    final var yOffset = Chunk.CHUNK_SECTION_SIZE * compound.getByte("Y");
                    final var states = compound.getCompound("block_states");
                    final var blockPalette = states.getList("palette");
                    if (blockPalette.equals(ListBinaryTag.empty())) continue;
                    final var blockStates = this.uncompressedBlockStates(states);
                    final var palette = new int[blockPalette.size()];

                    for (int i = 0; i < palette.length; i++) {
                        final var entry = blockPalette.getCompound(i);
                        final var block = entry.getString("Name");
                        palette[i] = VanillaLoader.this.server.getBlockRegistry().getId(block);
                        // ignore properties for now
                    }

                    for (int y = 0; y < CHUNK_SECTION_SIZE; y++) {
                        for (int z = 0; z < CHUNK_SECTION_SIZE; z++) {
                            for (int x = 0; x < CHUNK_SECTION_SIZE; x++) {
                                final var blockIndex = y * CHUNK_SECTION_SIZE * CHUNK_SECTION_SIZE + z * CHUNK_SECTION_SIZE + x;
                                final var paletteIndex = blockStates[blockIndex];
                                chunk.setBlock(x, y + yOffset, z, palette[paletteIndex]);
                            }
                        }
                    }
                }
                this.chunks.put(index, chunk);
            }
            return chunk;
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

        private long sizeInSectors(final int location) {
            return location & 0xFF;
        }

        private long sectorOffset(final int location) {
            return location >>> 8;
        }

    }
}
