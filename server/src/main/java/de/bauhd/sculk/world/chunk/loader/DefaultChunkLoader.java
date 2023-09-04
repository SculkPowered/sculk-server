package de.bauhd.sculk.world.chunk.loader;

import de.bauhd.sculk.world.MinecraftWorld;
import de.bauhd.sculk.world.chunk.ChunkGenerator;
import de.bauhd.sculk.world.chunk.MinecraftChunk;
import org.jetbrains.annotations.NotNull;

public class DefaultChunkLoader implements ChunkLoader {

    private final ChunkGenerator generator;

    public DefaultChunkLoader(final ChunkGenerator generator) {
        this.generator = generator;
    }

    @Override
    public @NotNull MinecraftChunk loadChunk(MinecraftWorld world, int x, int z) {
        final var chunk = new MinecraftChunk(world, x, z);
        this.generator.generate(chunk);
        return chunk;
    }

    @Override
    public @NotNull ChunkGenerator getGenerator() {
        return this.generator;
    }
}