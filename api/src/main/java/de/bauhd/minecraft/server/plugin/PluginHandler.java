package de.bauhd.minecraft.server.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Handles the plugins.
 */
public interface PluginHandler {

    /**
     * Check if a plugin is available.
     * @param name the plugin
     * @return {@code true} if its loaded
     */
    boolean isLoaded(@NotNull String name);

    /**
     * Gets a plugin by its name.
     * @param name the name of the plugin
     * @return the plugin or null
     */
    @Nullable Plugin getPlugin(@NotNull String name);

    /**
     * Gets a collection of all loaded plugins.
     * @return all loaded plugins
     */
    @NotNull Collection<Plugin> getPlugins();
}
