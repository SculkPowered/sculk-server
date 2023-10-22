package io.github.sculkpowered.server.world.chunk;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that generates chunks.
 */
public interface ChunkGenerator {

  /**
   * Generates a chunk.
   *
   * @param chunk the chunk that should be generated
   * @since 1.0.0
   */
  void generate(@NotNull Chunk chunk);
}
