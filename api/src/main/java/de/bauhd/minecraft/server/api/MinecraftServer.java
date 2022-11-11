package de.bauhd.minecraft.server.api;

import de.bauhd.minecraft.server.api.command.CommandHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;

public abstract class MinecraftServer {

    private static MinecraftServer instance;

    protected MinecraftServer() {
        instance = this;
    }

    public static MinecraftServer getInstance() {
        return instance;
    }

    public abstract DimensionHandler getDimensionHandler();

    public abstract BiomeHandler getBiomeHandler();

    public abstract CommandHandler getCommandHandler();
}
