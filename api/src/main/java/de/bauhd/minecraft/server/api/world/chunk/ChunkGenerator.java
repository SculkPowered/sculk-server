package de.bauhd.minecraft.server.api.world.chunk;

import org.jetbrains.annotations.NotNull;

public interface ChunkGenerator {

    void generate(@NotNull Chunk chunk);

}
