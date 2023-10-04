package de.bauhd.sculk.world;

import de.bauhd.sculk.entity.AbstractEntity;
import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.util.CoordinateUtil;
import de.bauhd.sculk.world.block.BlockState;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.SculkChunk;
import de.bauhd.sculk.world.chunk.loader.ChunkLoader;
import de.bauhd.sculk.world.dimension.Dimension;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class SculkWorld implements World {

    private final String name;
    private final Dimension dimension;
    private final ChunkLoader loader;
    private final Position spawnPosition;
    private final GameMode defaultGameMode;
    private final Long2ObjectMap<SculkChunk> chunks;
    private Worker worker;
    private boolean alive;

    public SculkWorld(final World.Builder builder, final ChunkLoader loader) {
        this.name = builder.name();
        this.dimension = builder.dimension();
        this.loader = loader;
        this.spawnPosition = builder.spawnPosition();
        this.defaultGameMode = builder.defaultGameMode();
        this.chunks = new Long2ObjectOpenHashMap<>();
        this.alive = true;
        this.worker = new Worker(this);
        this.worker.start();
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
    public @NotNull SculkChunk getChunk(int chunkX, int chunkZ) {
        var chunk = this.chunks.get(CoordinateUtil.chunkIndex(chunkX, chunkZ));
        if (chunk == null) {
            chunk = this.loadChunk(chunkX, chunkZ);
        }
        return chunk;
    }

    @Override
    public @NotNull SculkChunk getChunkAt(int x, int z) {
        return this.getChunk(CoordinateUtil.chunkCoordinate(x), CoordinateUtil.chunkCoordinate(z));
    }

    @Override
    public @NotNull SculkChunk getChunkAt(@NotNull Position position) {
        return this.getChunkAt((int) position.x(), (int) position.z());
    }

    @Override
    public void spawnEntity(@NotNull Entity entity, @NotNull Position position) {
        final var abstractEntity = (AbstractEntity) entity;
        abstractEntity.spawn(this, position);
        for (final var viewer : this.getChunkAt(position).viewers()) {
            abstractEntity.addViewer(viewer);
        }
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    private SculkChunk loadChunk(final int chunkX, final int chunkZ) {
        final var chunk = this.loader.loadChunk(this, chunkX, chunkZ);
        this.putChunk(chunk);
        return chunk;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Long2ObjectMap<SculkChunk> chunks() {
        return this.chunks;
    }

    public void putChunk(final SculkChunk chunk) {
        this.chunks.put(CoordinateUtil.chunkIndex(chunk.getX(), chunk.getZ()), chunk);
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
