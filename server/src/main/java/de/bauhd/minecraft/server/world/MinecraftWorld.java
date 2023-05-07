package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import org.jetbrains.annotations.NotNull;

public class MinecraftWorld implements World {

    private final AdvancedMinecraftServer server;
    private final String name;
    private final Dimension dimension;
    private final ChunkGenerator generator;
    private final Position spawnPosition;

    public MinecraftWorld(final AdvancedMinecraftServer server, final String name,
                          final Dimension dimension, final ChunkGenerator generator, final Position spawnPosition) {
        this.server = server;
        this.name = name;
        this.dimension = dimension;
        this.generator = generator;
        this.spawnPosition = spawnPosition;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public @NotNull ChunkGenerator getGenerator() {
        return this.generator;
    }

    @Override
    public @NotNull Position getSpawnPosition() {
        return this.spawnPosition;
    }

    @Override
    public @NotNull Block getBlock(int x, int y, int z) {
        return null;
    }

    public MinecraftChunk createChunk(final int chunkX, final int chunkZ) {
        final var chunk = new MinecraftChunk(this.server, this, chunkX, chunkZ);
        this.generator.generate(chunk);
        return chunk;
    }
}
