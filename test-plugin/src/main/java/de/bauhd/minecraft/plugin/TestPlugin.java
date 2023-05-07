package de.bauhd.minecraft.plugin;

import de.bauhd.minecraft.plugin.command.GameModeCommand;
import de.bauhd.minecraft.server.event.Subscribe;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.event.player.PlayerJoinEvent;
import de.bauhd.minecraft.server.plugin.Plugin;
import de.bauhd.minecraft.server.plugin.PluginDescription;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.World;
import de.bauhd.minecraft.server.world.biome.Biome;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.nio.file.Path;

@PluginDescription(name = "test", version = "1.0")
public final class TestPlugin extends Plugin {

    private World world;

    @Subscribe
    public void handle(final ServerInitializeEvent event) {
        this.getServer().getCommandHandler().register(new GameModeCommand());

        final var testBiome = Biome.PLAINS.toBuilder("test")
                .effects(Biome.Effects.builder()
                        .grassColor(0x962d26)
                        .skyColor(8103167)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .waterColor(4159204)
                )
                .build();
        this.getServer().getBiomeHandler().registerBiome(testBiome);
        this.world = this.getServer().loadWorld(World.builder()
                        .name("test")
                        .spawnPosition(new Position(0, 100, 0)),
                Path.of("world"));
    }

    @Subscribe
    public void handle(final PlayerJoinEvent event) {
        final var player = event.player();
        player.setWorld(this.world);
        player.sendMessage(Component.text("Welcome " + player.getUsername(), NamedTextColor.DARK_AQUA));
        player.sendPlayerListHeaderAndFooter(Component.text("Header", NamedTextColor.RED), Component.text("Footer", NamedTextColor.DARK_RED));
    }

}
