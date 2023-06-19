package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.world.dimension.Dimension;

public final class VanillaWorld extends MinecraftWorld {

    private final VanillaLoader loader;

    public VanillaWorld(final AdvancedMinecraftServer server, final String name,
                        final Dimension dimension, final ChunkGenerator generator,
                        final Position spawnPosition, final GameMode defaultGameMode, final VanillaLoader loader) {
        super(server, name, dimension, generator, spawnPosition, defaultGameMode);
        this.loader = loader;
        this.loader.setWorld(this);
    }

    @Override
    protected MinecraftChunk createChunk(int chunkX, int chunkZ) {
        var chunk = this.loader.getChunk(chunkX, chunkZ);
        if (chunk == null) {
            chunk = super.createChunk(chunkX, chunkZ);
        } else {
            this.put(chunk);
        }
        return chunk;
    }
}
