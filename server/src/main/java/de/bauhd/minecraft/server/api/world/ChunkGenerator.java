package de.bauhd.minecraft.server.api.world;

public final class ChunkGenerator {

    public void generate(final Chunk chunk, final int chunkX, final int chunkY) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBlock(x, 40, z, Material.STONE);
            }
        }
    }

}
