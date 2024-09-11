package eu.sculkpowered.server.world.chunk.loader;

import eu.sculkpowered.server.world.SculkWorld;
import eu.sculkpowered.server.world.chunk.ChunkGenerator;
import eu.sculkpowered.server.world.chunk.SculkChunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoader {

  @NotNull SculkChunk loadChunk(SculkWorld world, int x, int z);

  @NotNull ChunkGenerator getGenerator();
}
