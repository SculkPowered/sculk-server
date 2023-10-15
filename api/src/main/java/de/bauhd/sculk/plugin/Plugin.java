package de.bauhd.sculk.plugin;

import de.bauhd.sculk.MinecraftServer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

/**
 * Represents the main class of a plugin.
 */
public abstract class Plugin {

  private MinecraftServer server;
  private PluginDescription description;
  private ComponentLogger logger;
  private volatile ExecutorService executorService;

  final void init(final MinecraftServer server) {
    this.server = server;
    this.description = this.getClass().getAnnotation(PluginDescription.class);
    this.logger = ComponentLogger.logger(this.description.name());
  }

  /**
   * Gets the server instance.
   *
   * @return the server
   * @since 1.0.0
   */
  public final MinecraftServer getServer() {
    return server;
  }

  /**
   * Gets the description of the plugin.
   *
   * @return the plugin's description
   * @since 1.0.0
   */
  public final PluginDescription getDescription() {
    return this.description;
  }

  /**
   * Gets the {@link ComponentLogger} of the plugin.
   *
   * @return the plugin's logger
   * @since 1.0.0
   */
  public ComponentLogger getLogger() {
    return this.logger;
  }

  /**
   * Gets the {@link ExecutorService} of this plugin.
   *
   * @return the plugin's executor service
   * @since 1.0.0
   */
  public ExecutorService getExecutorService() {
    if (this.executorService == null) {
      synchronized (this) {
        if (this.executorService == null) {
          final var threadCount = new AtomicInteger();
          this.executorService = Executors.newCachedThreadPool(runnable -> {
            final var thread = new Thread(runnable,
                Plugin.this.description.name() + " - Executor " + threadCount.getAndIncrement());
            thread.setDaemon(true);
            return thread;
          });
        }
      }
    }
    return this.executorService;
  }
}
