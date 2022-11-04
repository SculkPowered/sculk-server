package de.bauhd.minecraft.server.api.world;

import java.util.function.BiConsumer;

public final class World {

    private final ChunkGenerator chunkGenerator;

    public World() {
        this.chunkGenerator = new ChunkGenerator();
    }

    public Chunk createChunk(final int chunkX, final int chunkZ) {
        final var chunk = new Chunk(chunkX, chunkZ);
        //this.chunkGenerator.generate(chunk, chunkX, chunkZ);
        return chunk;
    }

    public void chunksInRange(final int chunkX, final int chunkZ, final int range, final BiConsumer<Integer, Integer> chunk) {
        for (var x = -range; x <= range; x++) {
            for (var z = -range; z <= range; z++) {
                chunk.accept(chunkX + x, chunkZ + z);
            }
        }
    }

}
