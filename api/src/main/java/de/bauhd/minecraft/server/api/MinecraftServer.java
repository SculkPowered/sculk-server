package de.bauhd.minecraft.server.api;

import de.bauhd.minecraft.server.api.command.CommandHandler;
import de.bauhd.minecraft.server.api.event.EventHandler;
import de.bauhd.minecraft.server.api.module.ModuleHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import org.jetbrains.annotations.NotNull;

public interface MinecraftServer {

    @NotNull DimensionHandler getDimensionHandler();

    @NotNull BiomeHandler getBiomeHandler();

    @NotNull ModuleHandler getModuleHandler();

    @NotNull EventHandler getEventHandler();

    @NotNull CommandHandler getCommandHandler();
}
