package de.bauhd.minecraft.plugin.command;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.command.BrigadierCommand;
import de.bauhd.minecraft.server.container.AnvilContainer;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.container.item.ItemStack;
import de.bauhd.minecraft.server.container.item.Material;
import de.bauhd.minecraft.server.entity.player.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class AnvilCommand extends BrigadierCommand {

    public AnvilCommand(final MinecraftServer server) {
        super(literal("anvil").executes(context -> {
            if (context.getSource() instanceof Player player) {
                final var container = (AnvilContainer) server.createContainer(Container.Type.ANVIL, Component.text("No"));
                container.setRepairCost(4);
                container.setItem(0, ItemStack.of(Material.SHIELD).displayName(Component.text("Hallo", NamedTextColor.DARK_RED)));
                container.setItem(1, ItemStack.of(Material.DIAMOND).displayName(Component.text("Hallo", NamedTextColor.DARK_RED)));
                container.setItem(2, ItemStack.of(Material.SHIELD));

                player.openContainer(container);
            }
            return 1;
        }));
    }
}
