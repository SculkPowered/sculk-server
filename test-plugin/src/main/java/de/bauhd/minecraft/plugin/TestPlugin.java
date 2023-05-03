package de.bauhd.minecraft.plugin;

import de.bauhd.minecraft.plugin.command.GameModeCommand;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.event.Subscribe;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.event.player.PlayerJoinEvent;
import de.bauhd.minecraft.server.plugin.Plugin;
import de.bauhd.minecraft.server.plugin.PluginDescription;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@PluginDescription(name = "test", version = "1.0")
public final class TestPlugin extends Plugin {

    @Subscribe
    public void handle(final ServerInitializeEvent event) {
        this.getServer().getCommandHandler().register(new GameModeCommand());
    }

    @Subscribe
    public void handle(final PlayerJoinEvent event) {
        final var player = event.player();

        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(Component.text("Welcome " + player.getUsername(), NamedTextColor.DARK_AQUA));
        player.sendPlayerListHeaderAndFooter(Component.text("Header", NamedTextColor.RED), Component.text("Footer", NamedTextColor.DARK_RED));
    }

}
