package io.github.sculkpowered.server.world.chunk.loader;

import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.chunk.ChunkGenerator;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoader {

  @NotNull SculkChunk loadChunk(SculkWorld world, int x, int z);

  @NotNull ChunkGenerator getGenerator();
}
