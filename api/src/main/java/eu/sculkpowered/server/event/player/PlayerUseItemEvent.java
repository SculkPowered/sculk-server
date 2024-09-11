package eu.sculkpowered.server.event.player;

import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client uses an item.
 */
public final class PlayerUseItemEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final Player player;
  private final ItemStack item;

  public PlayerUseItemEvent(@NotNull Player player, @NotNull ItemStack item) {
    this.player = player;
    this.item = item;
    this.result = GenericResult.allow();
  }

  public @NotNull Player player() {
    return this.player;
  }

  public @NotNull ItemStack item() {
    return this.item;
  }
}
