package de.bauhd.minecraft.server.api;

import de.bauhd.minecraft.server.api.command.CommandHandler;
import de.bauhd.minecraft.server.api.entity.player.Player;
import de.bauhd.minecraft.server.api.event.EventHandler;
import de.bauhd.minecraft.server.api.plugin.PluginHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface MinecraftServer {

    @NotNull DimensionHandler getDimensionHandler();

    @NotNull BiomeHandler getBiomeHandler();

    @NotNull PluginHandler getPluginHandler();

    @NotNull EventHandler getEventHandler();

    @NotNull CommandHandler getCommandHandler();

    @NotNull Collection<? extends Player> getAllPlayers();

    int getPlayerCount();

    @Nullable Player getPlayer(@NotNull UUID uniqueId);
}
