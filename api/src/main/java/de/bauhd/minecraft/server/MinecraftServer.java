package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.command.CommandHandler;
import de.bauhd.minecraft.server.entity.Entity;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.event.EventHandler;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.plugin.PluginHandler;
import de.bauhd.minecraft.server.world.World;
import de.bauhd.minecraft.server.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.world.dimension.DimensionHandler;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;

public interface MinecraftServer {

    /**
     * Gets the {@link DimensionHandler} instance
     * @return the dimension handler instance
     */
    @NotNull DimensionHandler getDimensionHandler();

    /**
     * Gets the {@link BiomeHandler} instance
     * @return the biome handler instance
     */
    @NotNull BiomeHandler getBiomeHandler();

    /**
     * Gets the {@link PluginHandler} instance
     * @return the plugin handler instance
     */
    @NotNull PluginHandler getPluginHandler();

    /**
     * Gets the {@link EventHandler} instance
     * @return the event handler instance
     */
    @NotNull EventHandler getEventHandler();

    /**
     * Gets the {@link CommandHandler} instance
     * @return the command handler instance
     */
    @NotNull CommandHandler getCommandHandler();

    /**
     * Gets all players currently connected.
     * @return the players online
     */
    @NotNull Collection<? extends Player> getAllPlayers();

    /**
     * Gets the number of players currently connected.
     * @return the number of players
     */
    int getPlayerCount();

    /**
     * Gets a player by its unique id
     * @param uniqueId the unique id of the player
     * @return the player or null
     */
    @Nullable Player getPlayer(@NotNull UUID uniqueId);

    /**
     * Creates a world with the specified builder.
     * @param builder the builder for the world
     * @return the world
     */
    @NotNull World createWorld(@NotNull World.Builder builder);

    /**
     * Loads a vanilla world with the specified builder.
     * @param builder the builder for the world
     * @param path the path of the vanilla world
     * @return the loaded world
     */
    @NotNull World loadWorld(@NotNull World.Builder builder, @NotNull Path path);

    /**
     * Gets a world by its name.
     * @param name the name of the world
     * @return the world or null
     */
    @Nullable World getWorld(@NotNull String name);

    /**
     * Creates an entity by its class.
     * @param clazz the class of the entity
     * @return a new instance of the specified entity type
     * @param <T> the entity type
     */
    <T extends Entity> T createEntity(@NotNull Class<T> clazz);

    /**
     * Creates a container by its type.
     * @param type the type of the container
     * @param title the title of the container
     * @return a new instance of the container
     */
    @NotNull Container createContainer(@NotNull Container.Type type, @NotNull Component title);
}
