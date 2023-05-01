package de.bauhd.minecraft.server.plugin;

import de.bauhd.minecraft.server.MinecraftServer;

public abstract class Plugin {

    private MinecraftServer server;
    private PluginDescription description;

    final void init(final MinecraftServer server) {
        this.server = server;
        // todo logger
        this.description = this.getClass().getAnnotation(PluginDescription.class);
    }

    public final MinecraftServer getServer() {
        return server;
    }

    public final PluginDescription getDescription() {
        return this.description;
    }
}
