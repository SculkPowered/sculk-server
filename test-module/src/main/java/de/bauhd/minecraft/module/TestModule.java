package de.bauhd.minecraft.module;

import de.bauhd.minecraft.module.command.GameModeCommand;
import de.bauhd.minecraft.server.api.event.Subscribe;
import de.bauhd.minecraft.server.api.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.api.module.Module;
import de.bauhd.minecraft.server.api.module.ModuleDescription;

@ModuleDescription(name = "test", version = "1.0")
public final class TestModule extends Module {

    @Subscribe
    public void handle(final ServerInitializeEvent event) {
        this.getServer().getCommandHandler().register(new GameModeCommand());
    }

}
