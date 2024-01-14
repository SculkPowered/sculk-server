package io.github.sculkpowered.server.world;

import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.player.GameMode;
import io.github.sculkpowered.server.world.block.BlockState;
import io.github.sculkpowered.server.world.chunk.Chunk;
import io.github.sculkpowered.server.world.chunk.ChunkGenerator;
import io.github.sculkpowered.server.world.chunk.VoidGenerator;
import io.github.sculkpowered.server.world.dimension.Dimension;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a world.
 */
public interface World {

  /**
   * Gets the name of the world.
   *
   * @return the world's name
   * @since 1.0.0
   */
  @NotNull String name();

  /**
   * Gets the dimension of the world.
   *
   * @return the world's dimension
   * @since 1.0.0
   */
  @NotNull Dimension dimension();

  /**
   * Gets the chunk generator of the world.
   *
   * @return the world's chunk generator
   * @since 1.0.0
   */
  @NotNull ChunkGenerator generator();

  /**
   * Gets the spawn position of the world.
   *
   * @return the world's spawn position
   * @since 1.0.0
   */
  @NotNull Position spawnPosition();

  /**
   * Gets the default game mode of the world.
   *
   * @return the world's default game mode
   * @since 1.0.0
   */
  @NotNull GameMode defaultGameMode();

  /**
   * Sets the specified block at the specified coordinates.
   *
   * @param x     the x coordinate
   * @param y     the y coordinate
   * @param z     the z coordinate
   * @param block the block to set
   * @since 1.0.0
   */
  void block(int x, int y, int z, @NotNull BlockState block);

  /**
   * Sets the specified block at the specified coordinates.
   *
   * @param position the position
   * @param block    the block to set
   * @since 1.0.0
   */
  default void block(@NotNull Position position, @NotNull BlockState block) {
    this.block((int) position.x(), (int) position.y(), (int) position.z(), block);
  }

  /**
   * Gets the block at the specified coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @param z the z coordinate
   * @return the block at the specified coordinates
   * @since 1.0.0
   */
  @NotNull BlockState block(int x, int y, int z);

  /**
   * Gets the block at the specified position.
   *
   * @param position the position of the block
   * @return the block at the specified position
   * @since 1.0.0
   */
  default @NotNull BlockState block(@NotNull Position position) {
    return this.block((int) position.x(), (int) position.y(), (int) position.z());
  }

  /**
   * Gets the chunk at the specified chunk coordinates.
   *
   * @param chunkX the chunk x coordinate
   * @param chunkZ the chunk z coordinate
   * @return the chunk at the specified chunk coordinates
   * @since 1.0.0
   */
  @NotNull Chunk chunk(int chunkX, int chunkZ);

  /**
   * Gets the chunk at the specified coordinates.
   *
   * @param x the x coordinate
   * @param z the z coordinate
   * @return the chunk at the specified coordinates
   * @since 1.0.0
   */
  @NotNull Chunk chunkAt(int x, int z);

  /**
   * Gets the chunk at the specified position.
   *
   * @param position the position
   * @return the chunk at the specified position
   * @since 1.0.0
   */
  @NotNull Chunk chunkAt(@NotNull Position position);

  /**
   * Spawns the specified entity at the specified position in this world.
   *
   * @param entity   the entity to spawn
   * @param position the position
   * @since 1.0.0
   */
  void spawnEntity(@NotNull Entity entity, @NotNull Position position);

  /**
   * Gets the extra world data.
   * In the anvil folder it is the level dat file.
   *
   * @since 1.0.0
   */
  @NotNull CompoundBinaryTag extraData();

  /**
   * Sets the extra data.
   *
   * @param data the data in a compound binary tag
   * @since 1.0.0
   */
  void extraData(@NotNull CompoundBinaryTag data);

  /**
   * Checks if the world is alive.
   *
   * @return whether the world is alive
   * @since 1.0.0
   */
  boolean isAlive();

  /**
   * Saves a world.
   *
   * @since 1.0.0
   */
  void save(@NotNull WorldSaver saver);

  /**
   * Creates a new world builder.
   *
   * @return a new builder instance.
   * @since 1.0.0
   */
  static Builder builder() {
    return new Builder();
  }

  /**
   * Represents a world builder.
   */
  final class Builder {

    private String name;
    private Dimension dimension = Dimension.OVERWORLD;
    private WorldLoader loader;
    private ChunkGenerator generator = VoidGenerator.INSTANCE;
    private Position spawnPosition = Position.zero();
    private GameMode defaultGameMode = GameMode.SURVIVAL;

    public @NotNull Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    public @Nullable String name() {
      return this.name;
    }

    public @NotNull Builder dimension(@NotNull Dimension dimension) {
      this.dimension = dimension;
      return this;
    }

    public @NotNull Dimension dimension() {
      return this.dimension;
    }

    public @NotNull Builder generator(@NotNull ChunkGenerator generator) {
      this.generator = generator;
      return this;
    }

    public @NotNull Builder loader(@Nullable WorldLoader loader) {
      this.loader = loader;
      return this;
    }

    public @Nullable WorldLoader loader() {
      return this.loader;
    }

    public @NotNull ChunkGenerator generator() {
      return this.generator;
    }

    public @NotNull Builder spawnPosition(@NotNull Position position) {
      this.spawnPosition = position;
      return this;
    }

    public @NotNull Position spawnPosition() {
      return this.spawnPosition;
    }

    public @NotNull Builder defaultGameMode(@NotNull GameMode defaultGameMode) {
      this.defaultGameMode = defaultGameMode;
      return this;
    }

    public @NotNull GameMode defaultGameMode() {
      return this.defaultGameMode;
    }
  }
}
