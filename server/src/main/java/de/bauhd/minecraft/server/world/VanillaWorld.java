package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.world.dimension.Dimension;

public final class VanillaWorld extends MinecraftWorld {

    private final VanillaLoader loader;

    public VanillaWorld(final AdvancedMinecraftServer server, final String name,
                        final Dimension dimension, final ChunkGenerator generator,
                        final Position spawnPosition, final VanillaLoader loader) {
        super(server, name, dimension, generator, spawnPosition);
        this.loader = loader;
        this.loader.setWorld(this);
    }

    @Override
    public MinecraftChunk createChunk(int chunkX, int chunkZ) {
        var chunk = this.loader.getChunk(chunkX, chunkZ);
        if (chunk == null) {
            chunk = super.createChunk(chunkX, chunkZ);
        }
        return chunk;
    }
}
