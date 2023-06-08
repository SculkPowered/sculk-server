package de.bauhd.minecraft.server.event.player;

import de.bauhd.minecraft.server.container.item.ItemStack;
import de.bauhd.minecraft.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public final class PlayerUseItemEvent {

    private final @NotNull Player player;
    private final @NotNull ItemStack item;

    public PlayerUseItemEvent(@NotNull Player player, @NotNull ItemStack item) {
        this.player = player;
        this.item = item;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull ItemStack getItem() {
        return this.item;
    }
}
