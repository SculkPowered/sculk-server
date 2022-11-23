package de.bauhd.minecraft.server.api;

import de.bauhd.minecraft.server.api.command.CommandHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;

public interface MinecraftServer {

    DimensionHandler getDimensionHandler();

    BiomeHandler getBiomeHandler();

    CommandHandler getCommandHandler();
}
