package de.bauhd.minecraft.server.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface PluginHandler {

        @NotNull Collection<Plugin> plugins();

}
