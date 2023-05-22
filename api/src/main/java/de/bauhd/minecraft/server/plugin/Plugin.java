package de.bauhd.minecraft.server.plugin;

import de.bauhd.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Plugin {

    private MinecraftServer server;
    private PluginDescription description;
    private Logger logger;

    final void init(final MinecraftServer server) {
        this.server = server;
        this.description = this.getClass().getAnnotation(PluginDescription.class);
        this.logger = LoggerFactory.getLogger(this.description.name());
    }

    public final MinecraftServer getServer() {
        return server;
    }

    public final PluginDescription getDescription() {
        return this.description;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
