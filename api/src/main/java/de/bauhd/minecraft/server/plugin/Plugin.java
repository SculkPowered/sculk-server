package de.bauhd.minecraft.server.plugin;

import de.bauhd.minecraft.server.MinecraftServer;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;

public abstract class Plugin {

    private MinecraftServer server;
    private PluginDescription description;
    private ComponentLogger logger;

    final void init(final MinecraftServer server) {
        this.server = server;
        this.description = this.getClass().getAnnotation(PluginDescription.class);
        this.logger = ComponentLogger.logger(this.description.name());
    }

    public final MinecraftServer getServer() {
        return server;
    }

    public final PluginDescription getDescription() {
        return this.description;
    }

    public ComponentLogger getLogger() {
        return this.logger;
    }
}
