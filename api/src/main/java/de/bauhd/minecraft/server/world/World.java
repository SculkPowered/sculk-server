package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.world.chunk.Chunk;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.VoidGenerator;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface World {

    @NotNull String getName();

    @NotNull Dimension getDimension();

    @NotNull ChunkGenerator getGenerator();

    @NotNull Position getSpawnPosition();

    void setBlock(int x, int y, int z, Key key);

    default void setBlock(@NotNull Position position, @NotNull Key key) {
        this.setBlock((int) position.x(), (int) position.y(), (int) position.z(), key);
    }

    @NotNull Chunk getChunk(int chunkX, int chunkZ);

    @NotNull Chunk getChunkAt(int x, int z);

    default @NotNull Chunk getChunkAt(@NotNull Position position) {
        return this.getChunkAt((int) position.x(), (int) position.z());
    }

    static Builder builder() {
        return new Builder();
    }

    final class Builder {

        private String name;
        private Dimension dimension = Dimension.OVERWORLD;
        private ChunkGenerator generator = VoidGenerator.INSTANCE;
        private Position spawnPosition = Position.ZERO;

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
    }
}
