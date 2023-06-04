package de.bauhd.minecraft.server.event.player;

import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.container.Container;
import de.bauhd.minecraft.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class PlayerClickContainerEvent {

    private final @NotNull Player player;
    private final @NotNull Container container;
    private final @NotNull ItemStack carriedItem;
    private final short slot;
    private boolean cancelled;

    public PlayerClickContainerEvent(@NotNull Player player, @NotNull Container container, @NotNull ItemStack carriedItem, short slot) {
        this.player = player;
        this.container = container;
        this.carriedItem = carriedItem;
        this.slot = slot;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull Container getContainer() {
        return this.container;
    }

    public @NotNull ItemStack getCarriedItem() {
        return this.carriedItem;
    }

    public short getSlot() {
        return this.slot;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
