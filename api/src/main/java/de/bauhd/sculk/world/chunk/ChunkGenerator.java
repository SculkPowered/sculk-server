package de.bauhd.sculk.world.chunk;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that generates chunks.
 */
public interface ChunkGenerator {

    /**
     * Generates a chunk.
     * @param chunk the chunk that should be generated
     */
    void generate(@NotNull Chunk chunk);
}
