package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.command.CommandHandler;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.event.EventHandler;
import de.bauhd.minecraft.server.plugin.PluginHandler;
import de.bauhd.minecraft.server.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.world.dimension.DimensionHandler;
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
