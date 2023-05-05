package de.bauhd.minecraft.server.world.chunk;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public final class TestGenerator implements ChunkGenerator {

    private static final Key GRASS = Key.key("grass_block");
    private static final Key DIRT = Key.key("dirt");
    private static final Key STONE = Key.key("stone");
    private static final Key BEDROCK = Key.key("bedrock");

    @Override
    public void generate(@NotNull Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 5, z, GRASS);
                chunk.setBlock(x, 4, z, DIRT);
                chunk.setBlock(x, 3, z, STONE);
                chunk.setBlock(x, 2, z, STONE);
                chunk.setBlock(x, 1, z, STONE);
                chunk.setBlock(x, 0, z, BEDROCK);
            }
        }
    }
}
