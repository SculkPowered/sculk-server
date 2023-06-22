package de.bauhd.minecraft.server.world.chunk.loader;

import de.bauhd.minecraft.server.world.MinecraftWorld;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import org.jetbrains.annotations.NotNull;

public interface ChunkLoader {

    @NotNull MinecraftChunk loadChunk(MinecraftWorld world, int x, int z);

    @NotNull ChunkGenerator getGenerator();
}
