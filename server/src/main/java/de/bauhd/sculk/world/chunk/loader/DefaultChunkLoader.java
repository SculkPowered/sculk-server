package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.SculkChunk;
import org.jetbrains.annotations.NotNull;

public class DefaultChunkLoader implements ChunkLoader {

    private final ChunkGenerator generator;

    public DefaultChunkLoader(final ChunkGenerator generator) {
        this.generator = generator;
    }

    @Override
    public @NotNull SculkChunk loadChunk(SculkWorld world, int x, int z) {
        final var chunk = new SculkChunk(world, x, z);
        this.generator.generate(chunk);
        return chunk;
    }

    @Override
    public @NotNull ChunkGenerator getGenerator() {
        return this.generator;
    }
}
