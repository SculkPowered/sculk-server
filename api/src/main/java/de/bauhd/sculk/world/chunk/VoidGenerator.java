package de.bauhd.sculk.world.chunk;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link ChunkGenerator} that generates nothing.
 */
public final class VoidGenerator implements ChunkGenerator {

    public static final VoidGenerator INSTANCE = new VoidGenerator();

    @Override
    public void generate(@NotNull Chunk chunk) {}
}
