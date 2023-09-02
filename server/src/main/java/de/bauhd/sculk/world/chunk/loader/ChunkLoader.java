package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.world.MinecraftWorld;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.MinecraftChunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoader {

    @NotNull MinecraftChunk loadChunk(MinecraftWorld world, int x, int z);

    @NotNull ChunkGenerator getGenerator();
}
