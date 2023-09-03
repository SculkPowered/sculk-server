package de.bauhd.sculk.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

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
    boolean isLoaded(@NotNull String name);

    /**
     * Gets a plugin by its name.
     *
     * @param name the name of the plugin
     * @return the plugin or null
     * @since 1.0.0
     */
    @Nullable Plugin getPlugin(@NotNull String name);

    /**
     * Gets a collection of all loaded plugins.
     *
     * @return all loaded plugins
     * @since 1.0.0
     */
    @NotNull Collection<Plugin> getPlugins();
}
