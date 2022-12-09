package de.bauhd.minecraft.server.api.world;

import de.bauhd.minecraft.server.api.world.chunk.MinecraftChunk;

public final class ChunkGenerator {

    public void generate(final MinecraftChunk chunk, final int chunkX, final int chunkY) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 40, z, Material.STONE);
            }
        }
    }

}
