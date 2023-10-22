package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client uses an item.
 */
public final class PlayerUseItemEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull Player player;
  private final @NotNull ItemStack item;

  public PlayerUseItemEvent(@NotNull Player player, @NotNull ItemStack item) {
    this.player = player;
    this.item = item;
    this.result = GenericResult.allowed();
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }

  public @NotNull ItemStack getItem() {
    return this.item;
  }
}
