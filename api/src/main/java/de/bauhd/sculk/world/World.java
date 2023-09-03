package de.bauhd.sculk.world;

import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.world.block.BlockState;
import de.bauhd.sculk.world.chunk.Chunk;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.VoidGenerator;
import de.bauhd.sculk.world.dimension.Dimension;
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
    @NotNull String getName();

    /**
     * Gets the dimension of the world.
     *
     * @return the world's dimension
     * @since 1.0.0
     */
    @NotNull Dimension getDimension();

    /**
     * Gets the chunk generator of the world.
     *
     * @return the world's chunk generator
     * @since 1.0.0
     */
    @NotNull ChunkGenerator getGenerator();

    /**
     * Gets the spawn position of the world.
     *
     * @return the world's spawn position
     * @since 1.0.0
     */
    @NotNull Position getSpawnPosition();

    /**
     * Gets the default game mode of the world.
     *
     * @return the world's default game mode
     * @since 1.0.0
     */
    @NotNull GameMode getDefaultGameMode();

    /**
     * Sets the specified block at the specified coordinates.
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @param block the block to set
     * @since 1.0.0
     */
    void setBlock(int x, int y, int z, @NotNull BlockState block);

    /**
     * Sets the specified block at the specified coordinates.
     *
     * @param position the position
     * @param block    the block to set
     * @since 1.0.0
     */
    default void setBlock(@NotNull Position position, @NotNull BlockState block) {
        this.setBlock((int) position.x(), (int) position.y(), (int) position.z(), block);
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
    @NotNull BlockState getBlock(int x, int y, int z);

    /**
     * Gets the block at the specified position.
     *
     * @param position the position of the block
     * @return the block at the specified position
     * @since 1.0.0
     */
    default @NotNull BlockState getBlock(@NotNull Position position) {
        return this.getBlock((int) position.x(), (int) position.y(), (int) position.z());
    }

    /**
     * Gets the chunk at the specified chunk coordinates.
     *
     * @param chunkX the chunk x coordinate
     * @param chunkZ the chunk z coordinate
     * @return the chunk at the specified chunk coordinates
     * @since 1.0.0
     */
    @NotNull Chunk getChunk(int chunkX, int chunkZ);

    /**
     * Gets the chunk at the specified coordinates.
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @return the chunk at the specified coordinates
     * @since 1.0.0
     */
    @NotNull Chunk getChunkAt(int x, int z);

    /**
     * Gets the chunk at the specified position.
     *
     * @param position the position
     * @return the chunk at the specified position
     * @since 1.0.0
     */
    default @NotNull Chunk getChunkAt(@NotNull Position position) {
        return this.getChunkAt((int) position.x(), (int) position.z());
    }

    /**
     * Spawns the specified entity at the specified position in this world.
     *
     * @param entity   the entity to spawn
     * @param position the position
     * @since 1.0.0
     */
    void spawnEntity(@NotNull Entity entity, @NotNull Position position);

    /**
     * Checks if the world is alive.
     *
     * @return whether the world is alive
     * @since 1.0.0
     */
    boolean isAlive();

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
        private ChunkGenerator generator = VoidGenerator.INSTANCE;
        private Position spawnPosition = Position.ZERO;
        private GameMode defaultGameMode = GameMode.SURVIVAL;

        public Builder name(@NotNull String name) {
            this.name = name;
            return this;
        }

        public @Nullable String name() {
            return this.name;
        }

        public Builder dimension(@NotNull Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        public @NotNull Dimension dimension() {
            return this.dimension;
        }

        public Builder generator(@NotNull ChunkGenerator generator) {
            this.generator = generator;
            return this;
        }

        public @NotNull ChunkGenerator generator() {
            return this.generator;
        }

        public Builder spawnPosition(@NotNull Position position) {
            this.spawnPosition = position;
            return this;
        }

        public @NotNull Position spawnPosition() {
            return this.spawnPosition;
        }

        public Builder defaultGameMode(GameMode defaultGameMode) {
            this.defaultGameMode = defaultGameMode;
            return this;
        }

        public GameMode defaultGameMode() {
            return this.defaultGameMode;
        }
    }
}
