package io.github.sculkpowered.server.world;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.entity.AbstractEntity;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.player.GameMode;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.util.CoordinateUtil;
import io.github.sculkpowered.server.world.block.BlockState;
import io.github.sculkpowered.server.world.chunk.ChunkGenerator;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import io.github.sculkpowered.server.world.chunk.loader.ChunkLoader;
import io.github.sculkpowered.server.world.dimension.Dimension;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.function.Consumer;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class SculkWorld implements World {

  private final SculkServer server;
  private final String name;
  private final Dimension dimension;
  private final ChunkLoader loader;
  private final Position spawnPosition;
  private final GameMode defaultGameMode;
  private final Long2ObjectMap<SculkChunk> chunks;
  private CompoundBinaryTag extraData = CompoundBinaryTag.empty();
  private boolean alive;

  public SculkWorld(
      final SculkServer server,
      final World.Builder builder,
      final ChunkLoader loader
  ) {
    this.server = server;
    this.name = builder.name();
    this.dimension = builder.dimension();
    this.loader = loader;
    this.spawnPosition = builder.spawnPosition();
    this.defaultGameMode = builder.defaultGameMode();
    this.chunks = new Long2ObjectOpenHashMap<>();
    this.alive = true;
  }

  @Override
  public @NotNull String name() {
    return this.name;
  }

  @Override
  public @NotNull Dimension dimension() {
    return this.dimension;
  }

  @Override
  public @NotNull ChunkGenerator generator() {
    return this.loader.getGenerator();
  }

  @Override
  public @NotNull Position spawnPosition() {
    return this.spawnPosition;
  }

  @Override
  public @NotNull GameMode defaultGameMode() {
    return this.defaultGameMode;
  }

  @Override
  public void block(int x, int y, int z, @NotNull BlockState block) {
    final var chunk = this.chunkAt(x, z);
    synchronized (chunk) {
      chunk.block(x, y, z, block);
    }
  }

  @Override
  public @NotNull BlockState block(int x, int y, int z) {
    return this.chunkAt(x, z).block(x, y, z);
  }

  @Override
  public @NotNull SculkChunk chunk(int chunkX, int chunkZ) {
    var chunk = this.chunks.get(CoordinateUtil.chunkIndex(chunkX, chunkZ));
    if (chunk == null) {
      chunk = this.loadChunk(chunkX, chunkZ);
    }
    return chunk;
  }

  @Override
  public @NotNull SculkChunk chunkAt(int x, int z) {
    return this.chunk(CoordinateUtil.chunkCoordinate(x), CoordinateUtil.chunkCoordinate(z));
  }

  @Override
  public @NotNull SculkChunk chunkAt(@NotNull Position position) {
    return this.chunkAt((int) position.x(), (int) position.z());
  }

  @Override
  public void spawnEntity(@NotNull Entity entity, @NotNull Position position) {
    final var abstractEntity = (AbstractEntity) entity;
    abstractEntity.position = position;
    abstractEntity.world = this;
    this.chunkAt(position).entities().add(abstractEntity);
    for (final var viewer : this.chunkAt(position).viewers()) {
      abstractEntity.addViewer(viewer);
    }
  }

  @Override
  public @NotNull CompoundBinaryTag extraData() {
    return this.extraData;
  }

  @Override
  public void extraData(@NotNull CompoundBinaryTag data) {
    this.extraData = data;
  }

  @Override
  public boolean isAlive() {
    return this.alive;
  }

  @Override
  public void save(@NotNull WorldSaver saver) {
    if (saver instanceof WorldSaver.Slime slime) {
      SlimeFormat.save(this.server, this, slime.outputStream());
    } else if (saver instanceof WorldSaver.Anvil) {
      throw new UnsupportedOperationException("Anvil not implemented yet!");
    }
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
    this.chunks.put(CoordinateUtil.chunkIndex(chunk.x(), chunk.z()), chunk);
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
  }

  @Override
  public String toString() {
    return "SculkWorld{" +
        "name='" + this.name + '\'' +
        ", dimension=" + this.dimension +
        ", spawnPosition=" + this.spawnPosition +
        ", defaultGameMode=" + this.defaultGameMode +
        ", alive=" + this.alive +
        '}';
  }
}
