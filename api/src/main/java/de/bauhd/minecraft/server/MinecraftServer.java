package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.command.CommandHandler;
import de.bauhd.minecraft.server.command.CommandSource;
import de.bauhd.minecraft.server.damage.DamageType;
import de.bauhd.minecraft.server.entity.Entity;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.event.EventHandler;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.plugin.PluginHandler;
import de.bauhd.minecraft.server.registry.Registry;
import de.bauhd.minecraft.server.world.World;
import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Consumer;

public interface MinecraftServer {

    Consumer<Player> DEFAULT_WORLD_UNLOAD = player ->
            player.disconnect(Component.text("The world was unloaded!", NamedTextColor.RED));

    /**
     * Gets the registry of the dimensions
     * @return the dimension registry instance
     */
    @NotNull Registry<Dimension> getDimensionRegistry();

    /**
     * Gets the registry of the biomes
     * @return the biome registry instance
     */
    @NotNull Registry<Biome> getBiomeRegistry();

    /**
     * Gets the registry of the damage types
     * @return the damage type registry instance
     */
    @NotNull Registry<DamageType> getDamageTypeRegistry();

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
     * Loads the specified world.
     * @param world the world to load
     */
    void loadWorld(@NotNull World world);

    /**
     * Gets a world by its name.
     * @param name the name of the world
     * @return the world or null
     */
    @Nullable World getWorld(@NotNull String name);

    /**
     * Unloads the specified world.
     * @param world the world to unload
     * @param consumer the consumer to consume all players in the world
     */
    void unloadWorld(@NotNull World world, @NotNull Consumer<Player> consumer);

    /**
     * Unloads the specified world.
     * @param world the world to unload
     */
    default void unloadWorld(@NotNull World world) {
        this.unloadWorld(world, DEFAULT_WORLD_UNLOAD);
    }

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

    /**
     * Gets the console command source object.
     * @return the console command source object
     */
    @NotNull CommandSource getConsoleCommandSource();

    /**
     * Gets the configuration.
     * @return the configuration
     */
    @NotNull MinecraftConfig getConfig();

    /**
     * Shutdowns the server.
     */
    void shutdown();
}
