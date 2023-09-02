package de.bauhd.sculk.plugin.world;

import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.chunk.Chunk;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import static de.bauhd.sculk.world.block.Block.*;

public final class TestGenerator implements ChunkGenerator {

    private final Biome testBiome;

    public TestGenerator(final Biome testBiome) {
        this.testBiome = testBiome;
    }

    @Override
    public void generate(@NotNull Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 5, z, GRASS_BLOCK);
                chunk.setBlock(x, 4, z, DIRT);
                chunk.setBlock(x, 3, z, STONE);
                chunk.setBlock(x, 2, z, STONE);
                chunk.setBlock(x, 1, z, STONE);
                chunk.setBlock(x, 0, z, BEDROCK);
            }
        }
        chunk.setBiome(0, 5, 2, testBiome);
    }
}
