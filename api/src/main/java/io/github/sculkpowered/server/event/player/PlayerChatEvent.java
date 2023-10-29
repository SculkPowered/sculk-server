package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a player sends a chat message.
 */
public final class PlayerChatEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final Player player;
  private final String message;

  public PlayerChatEvent(@NotNull Player player, @NotNull String message) {
    this.player = player;
    this.message = message;
    this.result = GenericResult.allow();
  }

  public @NotNull Player player() {
    return this.player;
  }

  public @NotNull String message() {
    return this.message;
  }
}
