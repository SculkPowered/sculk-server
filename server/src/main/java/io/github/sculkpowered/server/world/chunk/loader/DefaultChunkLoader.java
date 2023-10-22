package io.github.sculkpowered.server.world.chunk.loader;

import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.chunk.ChunkGenerator;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import org.jetbrains.annotations.NotNull;

public class DefaultChunkLoader implements ChunkLoader {

  private final ChunkGenerator generator;

  public DefaultChunkLoader(final ChunkGenerator generator) {
    this.generator = generator;
  }

  @Override
  public @NotNull SculkChunk loadChunk(SculkWorld world, int x, int z) {
    final var chunk = new SculkChunk(world, x, z);
    this.generator.generate(chunk);
    return chunk;
  }

  @Override
  public @NotNull ChunkGenerator getGenerator() {
    return this.generator;
  }
}
