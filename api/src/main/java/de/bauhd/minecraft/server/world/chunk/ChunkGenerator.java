package de.bauhd.minecraft.server.world.chunk;

import org.jetbrains.annotations.NotNull;

public interface ChunkGenerator {

    void generate(@NotNull Chunk chunk);

}
