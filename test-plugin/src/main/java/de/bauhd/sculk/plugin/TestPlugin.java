package de.bauhd.sculk.plugin;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.container.AnvilContainer;
import de.bauhd.sculk.container.Container;
import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.container.item.Material;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.event.ResultedEvent;
import de.bauhd.sculk.event.Subscribe;
import de.bauhd.sculk.event.block.BlockBreakEvent;
import de.bauhd.sculk.event.block.BlockPlaceEvent;
import de.bauhd.sculk.event.connection.ServerPingEvent;
import de.bauhd.sculk.event.lifecycle.ServerInitializeEvent;
import de.bauhd.sculk.event.player.PlayerClickContainerEvent;
import de.bauhd.sculk.event.player.PlayerInitialEvent;
import de.bauhd.sculk.event.player.PlayerJoinEvent;
import de.bauhd.sculk.event.player.PlayerUseItemEvent;
import de.bauhd.sculk.plugin.command.GameModeCommand;
import de.bauhd.sculk.team.Team;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.World;
import de.bauhd.sculk.world.WorldLoader;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.block.Block;
import java.nio.file.Path;
import java.util.Objects;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

@PluginDescription(name = "test", version = "1.0")
public final class TestPlugin extends Plugin {

  private World world;

  @Subscribe
  public void handle(final ServerInitializeEvent event) {
    this.getServer().getCommandHandler()
        .register(GameModeCommand.get())
        .register(LiteralArgumentBuilder.<CommandSource>literal("test")
            .executes(context -> {
              if (context.getSource() instanceof Player player) {
                final var block = Block.COBBLESTONE_STAIRS.waterlogged(true);
                player.getWorld().setBlock(player.getPosition(), block);
              }
              return Command.SINGLE_SUCCESS;
            })
            .build());

    this.getServer().getTeamHandler().register(Team.builder()
        .name("default")
        .color(NamedTextColor.GRAY)
    );

    final var testBiome = Biome.PLAINS.toBuilder(Key.key("test", "plains"))
        .effects(Biome.Effects.builder()
            .grassColor(0x962d26)
            .skyColor(8103167)
            .waterFogColor(329011)
            .fogColor(12638463)
            .waterColor(4159204)
        )
        .build();
    this.getServer().getBiomeRegistry().register(testBiome);
    this.world = this.getServer().createWorld(World.builder()
        .name("test")
        .loader(WorldLoader.Slime.anvil(Path.of("world")))
        .spawnPosition(new Position(0.5, 86, 0.5))
    );
  }

  private final Component description = Component.text("Hello world!")
      .style(Style.style(NamedTextColor.RED, TextDecoration.BOLD));

  @Subscribe
  public void handle(final ServerPingEvent event) {
    event.setResponse(ServerPingEvent.response()
        .name("not vanilla")
        .max(10)
        .description(this.description));
  }

  @Subscribe
  public void handle(final PlayerInitialEvent event) {
    event.setWorld(this.world);
  }

  @Subscribe
  public void handle(final PlayerJoinEvent event) {
    final var player = event.getPlayer();
    player.setAllowFight(true);
    final var inventory = player.getInventory();
    inventory.setItem(4, ItemStack.of(Material.CHEST));
    inventory.setHelmet(ItemStack.of(Material.DIAMOND_HELMET));
    inventory.setItemInOffHand(ItemStack.of(Material.SHIELD));

    Objects.requireNonNull(this.getServer().getTeamHandler().getTeam("default"))
        .addEntry(event.getPlayer().getUsername());
  }

  @Subscribe
  public void handle(final PlayerUseItemEvent event) {
    if (event.getItem().material() == Material.CHEST) {
      final var container = (AnvilContainer) this.getServer()
          .createContainer(Container.Type.ANVIL, Component.text("No"));
      container.setRepairCost(4);
      container.setItem(0, ItemStack.of(Material.SHIELD)
          .displayName(Component.text("Hello", NamedTextColor.DARK_RED)));
      container.setItem(1,
          ItemStack.of(Material.DIAMOND).displayName(Component.text("Bye", NamedTextColor.RED)));
      container.setItem(2, ItemStack.of(Material.SHIELD));

      event.getPlayer().openContainer(container);
    }
  }

  @Subscribe
  public void handle(final PlayerClickContainerEvent event) {
    event.setResult(ResultedEvent.GenericResult.denied());
  }

  @Subscribe
  public void handle(final BlockBreakEvent event) {
    if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
      event.setResult(ResultedEvent.GenericResult.denied());
    }
  }

  @Subscribe
  public void handle(final BlockPlaceEvent event) {
    if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
      event.setResult(ResultedEvent.GenericResult.denied());
    }
  }
}
