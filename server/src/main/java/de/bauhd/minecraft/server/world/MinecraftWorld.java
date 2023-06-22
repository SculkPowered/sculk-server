package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.entity.AbstractEntity;
import de.bauhd.minecraft.server.entity.Entity;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.world.block.BlockState;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.world.chunk.loader.ChunkLoader;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MinecraftWorld implements World {

    private final String name;
    private final Dimension dimension;
    private final ChunkLoader loader;
    private final Position spawnPosition;
    private final GameMode defaultGameMode;
    private final Long2ObjectMap<MinecraftChunk> chunks;
    private Worker worker;
    private boolean alive;

    public MinecraftWorld(final String name, final Dimension dimension,
                          final ChunkLoader loader, final Position spawnPosition, final GameMode defaultGameMode) {
        this.name = name;
        this.dimension = dimension;
        this.loader = loader;
        this.spawnPosition = spawnPosition;
        this.defaultGameMode = defaultGameMode;
        this.chunks = new Long2ObjectOpenHashMap<>();
        this.load();
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
        return this.loader.getGenerator();
    }

    @Override
    public @NotNull Position getSpawnPosition() {
        return this.spawnPosition;
    }

    @Override
    public @NotNull GameMode getDefaultGameMode() {
        return this.defaultGameMode;
    }

    @Override
    public void setBlock(int x, int y, int z, @NotNull BlockState block) {
        final var chunk = this.getChunkAt(x, z);
        synchronized (chunk) {
            chunk.setBlock(x, y, z, block);
        }
    }

    @Override
    public @NotNull BlockState getBlock(int x, int y, int z) {
        return this.getChunkAt(x, z).getBlock(x, y, z);
    }

    @Override
    public @NotNull MinecraftChunk getChunk(int chunkX, int chunkZ) {
        var chunk = this.chunks.get(this.chunkIndex(chunkX, chunkZ));
        if (chunk == null) {
            chunk = this.loadChunk(chunkX, chunkZ);
        }
        return chunk;
    }

    @Override
    public @NotNull MinecraftChunk getChunkAt(int x, int z) {
        return this.getChunk(this.chunkCoordinate(x), this.chunkCoordinate(z));
    }

    @Override
    public void spawnEntity(@NotNull Entity entity, @NotNull Position position) {
        final var abstractEntity = (AbstractEntity) entity;
        abstractEntity.setWorld(this);
        abstractEntity.setPosition(position);
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    protected MinecraftChunk loadChunk(final int chunkX, final int chunkZ) {
        final var chunk = this.loader.loadChunk(this, chunkX, chunkZ);
        this.chunks.put(this.chunkIndex(chunk.getX(), chunk.getZ()), chunk);
        return chunk;
    }

    public int chunkCoordinate(final int coordinate) {
        return coordinate >> 4;
    }

    public long chunkIndex(final int x, final int z) {
        return ((((long) x) << 32) | (z & 0xFFFFFFFFL));
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Long2ObjectMap<MinecraftChunk> chunks() {
        return this.chunks;
    }

    public void load() {
        if (!this.alive) {
            synchronized (this) {
                this.alive = true;
                this.worker = new Worker(this);
                this.worker.start();
            }
        }
    }

    public void unload(@NotNull Consumer<Player> consumer) {
        this.alive = false;
        for (final var chunk : this.chunks.values()) {
            for (final var entity : chunk.entities()) {
                if (entity instanceof Player player) {
                    consumer.accept(player);
                }
            }
        }
        this.chunks.clear();
        this.worker = null;
    }
}
