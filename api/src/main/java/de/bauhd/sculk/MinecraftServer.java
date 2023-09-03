package de.bauhd.sculk;

import de.bauhd.sculk.command.CommandHandler;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.container.Container;
import de.bauhd.sculk.damage.DamageType;
import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.event.EventHandler;
import de.bauhd.sculk.plugin.PluginHandler;
import de.bauhd.sculk.registry.Registry;
import de.bauhd.sculk.world.World;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.dimension.Dimension;
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
     *
     * @return the dimension registry instance
     * @since 1.0.0
     */
    @NotNull Registry<Dimension> getDimensionRegistry();

    /**
     * Gets the registry of the biomes
     *
     * @return the biome registry instance
     * @since 1.0.0
     */
    @NotNull Registry<Biome> getBiomeRegistry();

    /**
     * Gets the registry of the damage types
     *
     * @return the damage type registry instance
     * @since 1.0.0
     */
    @NotNull Registry<DamageType> getDamageTypeRegistry();

    /**
     * Gets the {@link PluginHandler} instance
     *
     * @return the plugin handler instance
     * @since 1.0.0
     */
    @NotNull PluginHandler getPluginHandler();

    /**
     * Gets the {@link EventHandler} instance
     *
     * @return the event handler instance
     * @since 1.0.0
     */
    @NotNull EventHandler getEventHandler();

    /**
     * Gets the {@link CommandHandler} instance
     *
     * @return the command handler instance
     * @since 1.0.0
     */
    @NotNull CommandHandler getCommandHandler();

    /**
     * Gets all players currently connected.
     *
     * @return the players online
     * @since 1.0.0
     */
    @NotNull Collection<? extends Player> getAllPlayers();

    /**
     * Gets the number of players currently connected.
     *
     * @return the number of players
     * @since 1.0.0
     */
    int getPlayerCount();

    /**
     * Gets a player by its unique id
     *
     * @param uniqueId the unique id of the player
     * @return the player or null
     * @since 1.0.0
     */
    @Nullable Player getPlayer(@NotNull UUID uniqueId);

    /**
     * Creates a world with the specified builder.
     *
     * @param builder the builder for the world
     * @return the world
     * @since 1.0.0
     */
    @NotNull World createWorld(@NotNull World.Builder builder);

    /**
     * Loads a vanilla world with the specified builder.
     *
     * @param builder the builder for the world
     * @param path    the path of the vanilla world
     * @return the loaded world
     * @since 1.0.0
     */
    @NotNull World loadWorld(@NotNull World.Builder builder, @NotNull Path path);

    /**
     * Loads the specified world.
     *
     * @param world the world to load
     * @since 1.0.0
     */
    void loadWorld(@NotNull World world);

    /**
     * Gets a world by its name.
     *
     * @param name the name of the world
     * @return the world or null
     * @since 1.0.0
     */
    @Nullable World getWorld(@NotNull String name);

    /**
     * Unloads the specified world.
     *
     * @param world    the world to unload
     * @param consumer the consumer to consume all players in the world
     * @since 1.0.0
     */
    void unloadWorld(@NotNull World world, @NotNull Consumer<Player> consumer);

    /**
     * Unloads the specified world.
     *
     * @param world the world to unload
     * @since 1.0.0
     */
    default void unloadWorld(@NotNull World world) {
        this.unloadWorld(world, DEFAULT_WORLD_UNLOAD);
    }

    /**
     * Creates an entity by its class.
     *
     * @param clazz the class of the entity
     * @param <T>   the entity type
     * @return a new instance of the specified entity type
     * @since 1.0.0
     */
    <T extends Entity> T createEntity(@NotNull Class<T> clazz);

    /**
     * Creates a container by its type.
     *
     * @param type  the type of the container
     * @param title the title of the container
     * @return a new instance of the container
     * @since 1.0.0
     */
    @NotNull Container createContainer(@NotNull Container.Type type, @NotNull Component title);

    /**
     * Gets the console command source object.
     *
     * @return the console command source object
     * @since 1.0.0
     */
    @NotNull CommandSource getConsoleCommandSource();

    /**
     * Gets the configuration.
     *
     * @return the configuration
     * @since 1.0.0
     */
    @NotNull MinecraftConfig getConfig();

    /**
     * Shutdowns the server.
     *
     * @since 1.0.0
     */
    void shutdown();
}
