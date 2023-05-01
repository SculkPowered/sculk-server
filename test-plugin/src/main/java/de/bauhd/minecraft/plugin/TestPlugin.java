package de.bauhd.minecraft.plugin;

import de.bauhd.minecraft.plugin.command.GameModeCommand;
import de.bauhd.minecraft.server.event.Subscribe;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.plugin.Plugin;
import de.bauhd.minecraft.server.plugin.PluginDescription;

@PluginDescription(name = "test", version = "1.0")
public final class TestPlugin extends Plugin {

    @Subscribe
    public void handle(final ServerInitializeEvent event) {
        this.getServer().getCommandHandler().register(new GameModeCommand());
    }

}
