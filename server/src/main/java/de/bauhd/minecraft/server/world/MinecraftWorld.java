package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.world.chunk.Chunk;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;

public class MinecraftWorld implements World {

    private final AdvancedMinecraftServer server;
    private final String name;
    private final Dimension dimension;
    private final ChunkGenerator generator;
    private final Position spawnPosition;
    private final Long2ObjectMap<Chunk> chunks;

    public MinecraftWorld(final AdvancedMinecraftServer server, final String name,
                          final Dimension dimension, final ChunkGenerator generator, final Position spawnPosition) {
        this.server = server;
        this.name = name;
        this.dimension = dimension;
        this.generator = generator;
        this.spawnPosition = spawnPosition;
        this.chunks = new Long2ObjectOpenHashMap<>();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public @NotNull ChunkGenerator getGenerator() {
        return this.generator;
    }

    @Override
    public @NotNull Position getSpawnPosition() {
        return this.spawnPosition;
    }

    @Override
    public void setBlock(int x, int y, int z, @NotNull Block block) {
        final var chunk = this.getChunkAt(x, z);
        synchronized (chunk) {
            chunk.setBlock(x, y, z, block);
        }
    }

    @Override
    public @NotNull MinecraftChunk getChunk(int chunkX, int chunkZ) {
        var chunk = this.chunks.get(this.chunkIndex(chunkX, chunkZ));
        if (chunk == null) {
            chunk = this.createChunk(chunkX, chunkZ);
        }
        return (MinecraftChunk) chunk;
    }

    @Override
    public @NotNull MinecraftChunk getChunkAt(int x, int z) {
        return this.getChunk(this.chunkCoordinate(x), this.chunkCoordinate(z));
    }

    protected MinecraftChunk createChunk(final int chunkX, final int chunkZ) {
        final var chunk = new MinecraftChunk(this.server, this, chunkX, chunkZ);
        this.generator.generate(chunk);
        this.put(chunk);
        return chunk;
    }

    public void put(final MinecraftChunk chunk) {
        this.chunks.put(this.chunkIndex(chunk.getX(), chunk.getZ()), chunk);
    }

    public int chunkCoordinate(final int coordinate) {
        return coordinate >> 4;
    }

    public long chunkIndex(final int x, final int z) {
        return ((((long) x) << 32) | (z & 0xFFFFFFFFL));
    }
}
