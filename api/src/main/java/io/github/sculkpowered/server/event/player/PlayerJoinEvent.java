package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client reaches the join state.
 */
@SuppressWarnings("ClassCanBeRecord")
public final class PlayerJoinEvent {

  private final @NotNull Player player;

  public PlayerJoinEvent(@NotNull Player player) {
    this.player = player;
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }
}
