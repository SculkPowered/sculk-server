package de.bauhd.minecraft.server.api.world;

import de.bauhd.minecraft.server.api.world.block.Block;
import de.bauhd.minecraft.server.api.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.api.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.api.world.chunk.TestGenerator;
import de.bauhd.minecraft.server.api.world.dimension.Dimension;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public final class MinecraftWorld implements World {

    private final Dimension dimension = Dimension.OVERWORLD;
    private final ChunkGenerator generator;

    public MinecraftWorld() {
        this.generator = new TestGenerator();
    }

    @Override
    public @NotNull Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        return null;
    }

    public MinecraftChunk createChunk(final int chunkX, final int chunkZ) {
        final var chunk = new MinecraftChunk(this, chunkX, chunkZ);
        this.generator.generate(chunk);
        return chunk;
    }

    public void forChunksInRange(final int chunkX, final int chunkZ, final int range, final BiConsumer<Integer, Integer> chunk) {
        for (var x = -range; x <= range; x++) {
            for (var z = -range; z <= range; z++) {
                chunk.accept(chunkX + x, chunkZ + z);
            }
        }
    }
}
