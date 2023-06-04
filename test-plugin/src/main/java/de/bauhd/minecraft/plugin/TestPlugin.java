package de.bauhd.minecraft.plugin;

import de.bauhd.minecraft.plugin.command.AnvilCommand;
import de.bauhd.minecraft.plugin.command.GameModeCommand;
import de.bauhd.minecraft.server.container.item.ItemStack;
import de.bauhd.minecraft.server.container.item.Material;
import de.bauhd.minecraft.server.event.Subscribe;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.event.player.PlayerClickContainerEvent;
import de.bauhd.minecraft.server.event.player.PlayerJoinEvent;
import de.bauhd.minecraft.server.event.player.PlayerSpawnEvent;
import de.bauhd.minecraft.server.plugin.Plugin;
import de.bauhd.minecraft.server.plugin.PluginDescription;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.World;
import de.bauhd.minecraft.server.world.biome.Biome;

import java.nio.file.Path;

@PluginDescription(name = "test", version = "1.0")
public final class TestPlugin extends Plugin {

    private World world;

    @Subscribe
    public void handle(final ServerInitializeEvent event) {
        this.getServer().getCommandHandler()
                .register(new GameModeCommand())
                .register(new AnvilCommand(this.getServer()));

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
                        .spawnPosition(new Position(0.5, 86, 0.5)),
                Path.of("world"));
    }

    @Subscribe
    public void handle(final PlayerJoinEvent event) {
        event.player().setWorld(this.world);
    }

    @Subscribe
    public void handle(final PlayerSpawnEvent event) {
        final var player = event.player();
        player.setAllowFight(true);
        player.setInstantBreak(true);
        final var inventory = player.getInventory();
        inventory.setItem(4, ItemStack.of(Material.CHEST));
        inventory.setHelmet(ItemStack.of(Material.DIAMOND_HELMET));
        inventory.setItemInOffHand(ItemStack.of(Material.SHIELD));
    }

    @Subscribe
    public void handle(final PlayerClickContainerEvent event) {
        event.setCancelled(true);
    }
}
