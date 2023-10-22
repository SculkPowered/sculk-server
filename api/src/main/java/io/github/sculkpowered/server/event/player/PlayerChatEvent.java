package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a player sends a chat message.
 */
public final class PlayerChatEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull Player player;
  private final @NotNull String message;

  public PlayerChatEvent(@NotNull Player player, @NotNull String message) {
    this.player = player;
    this.message = message;
    this.result = GenericResult.allowed();
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }

  public @NotNull String getMessage() {
    return this.message;
  }
}
