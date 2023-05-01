package de.bauhd.minecraft.server.world.chunk;

import org.jetbrains.annotations.NotNull;

public final class TestGenerator implements ChunkGenerator {

    @Override
    public void generate(@NotNull Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 5, z, 9);
                chunk.setBlock(x, 4, z, 10);
                chunk.setBlock(x, 3, z, 1);
                chunk.setBlock(x, 2, z, 1);
                chunk.setBlock(x, 1, z, 1);
                chunk.setBlock(x, 0, z, 74);
            }
        }
    }
}
