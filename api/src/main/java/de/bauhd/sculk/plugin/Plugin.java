package de.bauhd.sculk.plugin;

import de.bauhd.sculk.MinecraftServer;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

/**
 * Represents the main class of a plugin.
 */
public abstract class Plugin {

    private MinecraftServer server;
    private PluginDescription description;
    private ComponentLogger logger;

    final void init(final MinecraftServer server) {
        this.server = server;
        this.description = this.getClass().getAnnotation(PluginDescription.class);
        this.logger = ComponentLogger.logger(this.description.name());
    }

    /**
     * Gets the server instance.
     * @return the server
     */
    public final MinecraftServer getServer() {
        return server;
    }

    /**
     * Gets the description of the plugin.
     * @return the plugin's description
     */
    public final PluginDescription getDescription() {
        return this.description;
    }

    /**
     * Gets the {@link ComponentLogger} of the plugin.
     * @return the plugin's logger
     */
    public ComponentLogger getLogger() {
        return this.logger;
    }
}
