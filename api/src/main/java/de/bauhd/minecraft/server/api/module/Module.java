package de.bauhd.minecraft.server.api.module;

import de.bauhd.minecraft.server.api.MinecraftServer;

public abstract class Module {

    private MinecraftServer server;
    private ModuleDescription description;

    final void init(final MinecraftServer server) {
        this.server = server;
        // todo logger
        this.description = this.getClass().getAnnotation(ModuleDescription.class);
    }

    public final MinecraftServer getServer() {
        return server;
    }

    public final ModuleDescription getDescription() {
        return this.description;
    }
}
