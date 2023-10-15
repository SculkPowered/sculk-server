package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.SculkChunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoader {

  @NotNull SculkChunk loadChunk(SculkWorld world, int x, int z);

  @NotNull ChunkGenerator getGenerator();
}
