package de.bauhd.minecraft.server.world.chunk;

import org.jetbrains.annotations.NotNull;

public final class VoidGenerator implements ChunkGenerator {

    public static final VoidGenerator INSTANCE = new VoidGenerator();

    @Override
    public void generate(@NotNull Chunk chunk) {}
}