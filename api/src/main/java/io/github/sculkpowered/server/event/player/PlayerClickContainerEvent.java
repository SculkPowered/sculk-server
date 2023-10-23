package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client clicks at an item in a container.
 */
public final class PlayerClickContainerEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull Player player;
  private final @NotNull Container container;
  private final @NotNull ItemStack carriedItem;
  private final short slot;

  public PlayerClickContainerEvent(@NotNull Player player, @NotNull Container container,
      @NotNull ItemStack carriedItem, short slot) {
    this.player = player;
    this.container = container;
    this.carriedItem = carriedItem;
    this.slot = slot;
    this.result = GenericResult.allowed();
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
}