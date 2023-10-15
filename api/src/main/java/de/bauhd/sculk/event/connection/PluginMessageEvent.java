package de.bauhd.sculk.event.connection;

import de.bauhd.sculk.entity.player.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("ClassCanBeRecord")
public final class PluginMessageEvent {

  private final @NotNull Player player;
  private final @NotNull String identifier;
  private final byte @NotNull [] data;

  public PluginMessageEvent(final @NotNull Player player, final @NotNull String identifier,
      final byte @NotNull [] data) {
    this.player = player;
    this.identifier = identifier;
    this.data = data;
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }

  public @NotNull String getIdentifier() {
    return this.identifier;
  }

  public byte[] getData() {
    return this.data;
  }
}
