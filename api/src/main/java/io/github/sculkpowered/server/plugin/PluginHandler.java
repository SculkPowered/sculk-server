package io.github.sculkpowered.server.plugin;

import java.nio.file.Path;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Handles the plugins.
 */
public interface PluginHandler {

  /**
   * Check if a plugin is available.
   *
   * @param name the plugin
   * @return {@code true} if its loaded
   * @since 1.0.0
   */
  boolean loaded(@NotNull String name);

  /**
   * Gets a plugin by its name.
   *
   * @param name the name of the plugin
   * @return the plugin or null
   * @since 1.0.0
   */
  @Nullable Plugin plugin(@NotNull String name);

  /**
   * Gets a collection of all loaded plugins.
   *
   * @return all loaded plugins
   * @since 1.0.0
   */
  @NotNull Collection<Plugin> plugins();

  /**
   * Adds the specified {@link Path} to the plugins classpath.
   *
   * @param plugin the plugin
   * @param path   the path
   */
  void addToClassPath(@NotNull Plugin plugin, @NotNull Path path);
}
