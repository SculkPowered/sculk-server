package eu.sculkpowered.server.plugin;

import eu.sculkpowered.server.Server;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

/**
 * Represents the main class of a plugin.
 */
public abstract class Plugin {

  private Server server;
  private PluginDescription description;
  private ComponentLogger logger;
  private Path dataDirectory;
  private volatile ExecutorService executorService;

  final void init(final Server server, final Path pluginDirectory) {
    this.server = server;
    this.description = this.getClass().getAnnotation(PluginDescription.class);
    this.logger = ComponentLogger.logger(this.description.name());
    this.dataDirectory = pluginDirectory.resolve(this.description.name());
  }

  /**
   * Gets the server instance.
   *
   * @return the server
   * @since 1.0.0
   */
  public final Server server() {
    return server;
  }

  /**
   * Gets the description of the plugin.
   *
   * @return the plugin's description
   * @since 1.0.0
   */
  public final PluginDescription description() {
    return this.description;
  }

  /**
   * Gets the {@link ComponentLogger} of the plugin.
   *
   * @return the plugin's logger
   * @since 1.0.0
   */
  public ComponentLogger logger() {
    return this.logger;
  }

  /**
   * Gets the data directory of this plugin.
   *
   * @return the plugin's data directory
   * @since 1.0.0
   */
  public Path dataDirectory() {
    return this.dataDirectory;
  }

  /**
   * Gets the {@link ExecutorService} of this plugin.
   *
   * @return the plugin's executor service
   * @since 1.0.0
   */
  public ExecutorService executorService() {
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

  public boolean hasExecutorService() {
    return this.executorService != null;
  }
}
