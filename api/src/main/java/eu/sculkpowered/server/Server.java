package eu.sculkpowered.server;

import eu.sculkpowered.server.command.CommandHandler;
import eu.sculkpowered.server.command.CommandSource;
import eu.sculkpowered.server.container.Container;
import eu.sculkpowered.server.entity.Entity;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.event.EventHandler;
import eu.sculkpowered.server.plugin.PluginHandler;
import eu.sculkpowered.server.scheduler.Scheduler;
import eu.sculkpowered.server.scoreboard.DisplaySlot;
import eu.sculkpowered.server.scoreboard.NumberFormat;
import eu.sculkpowered.server.scoreboard.Scoreboard;
import eu.sculkpowered.server.team.TeamHandler;
import eu.sculkpowered.server.world.World;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Consumer;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Server extends ForwardingAudience {

  Consumer<Player> DEFAULT_WORLD_UNLOAD = player ->
      player.disconnect(Component.text("The world was unloaded!", NamedTextColor.RED));

  /**
   * Gets the {@link PluginHandler} instance
   *
   * @return the plugin handler instance
   * @since 1.0.0
   */
  @NotNull PluginHandler pluginHandler();

  /**
   * Gets the {@link EventHandler} instance
   *
   * @return the event handler instance
   * @since 1.0.0
   */
  @NotNull EventHandler eventHandler();

  /**
   * Gets the {@link CommandHandler} instance
   *
   * @return the command handler instance
   * @since 1.0.0
   */
  @NotNull CommandHandler commandHandler();

  /**
   * Gets the {@link TeamHandler} instance
   *
   * @return the team handler instance
   * @since 1.0.0
   */
  @NotNull TeamHandler teamHandler();

  /**
   * Gets the {@link Scheduler} instance
   *
   * @return the scheduler instance
   * @since 1.0.0
   */
  @NotNull Scheduler scheduler();

  /**
   * Gets all players currently connected.
   *
   * @return the players online
   * @since 1.0.0
   */
  @NotNull Collection<? extends Player> onlinePlayers();

  /**
   * Gets the number of players currently connected.
   *
   * @return the number of players
   * @since 1.0.0
   */
  int playerCount();

  /**
   * Gets a player by its unique id
   *
   * @param uniqueId the unique id of the player
   * @return the player or null
   * @since 1.0.0
   */
  @Nullable Player player(@NotNull UUID uniqueId);

  /**
   * Creates a world with the specified builder.
   *
   * @param builder the builder for the world
   * @return the world
   * @since 1.0.0
   */
  @NotNull World createWorld(@NotNull World.Builder builder);

  /**
   * Gets a world by its name.
   *
   * @param name the name of the world
   * @return the world or null
   * @since 1.0.0
   */
  @Nullable World world(@NotNull String name);

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
   * Gets an entity by its id.
   *
   * @param id the id of the entity
   * @return the entity or null
   * @since 1.0.0
   */
  @Nullable Entity entity(int id);

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
  @NotNull CommandSource consoleCommandSource();

  /**
   * Gets the configuration.
   *
   * @return the configuration
   * @since 1.0.0
   */
  @NotNull MinecraftConfig config();

  /**
   * Creates a new scoreboard/objective with the specified attributes.
   *
   * @param name the name of the objective
   * @param displayName the display name of the objective
   * @param numberFormat the number format or null for the default one
   * @param displaySlot the display slot of the objective
   * @return the newly created scoreboard
   * @since 1.0.0
   */
  @NotNull Scoreboard createScoreboard(@NotNull String name, @NotNull Component displayName,
      @Nullable NumberFormat numberFormat, @NotNull DisplaySlot displaySlot);

  /**
   * Shutdowns the server.
   *
   * @since 1.0.0
   */
  void shutdown();
}
