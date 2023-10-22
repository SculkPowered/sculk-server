package io.github.sculkpowered.server.event.player;

import io.github.sculkpowered.server.entity.player.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassCanBeRecord")
public final class PlayerDisconnectEvent {

  private final @NotNull Player player;

  public PlayerDisconnectEvent(@NotNull Player player) {
    this.player = player;
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }
}
