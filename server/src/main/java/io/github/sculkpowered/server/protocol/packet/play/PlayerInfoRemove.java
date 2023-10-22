package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.entity.player.PlayerInfoEntry;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.List;

public final class PlayerInfoRemove implements Packet {

  private final List<PlayerInfoEntry> entries;

  public PlayerInfoRemove(final List<PlayerInfoEntry> entries) {
    this.entries = entries;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entries.size());
    for (final var entry : this.entries) {
      buf.writeUniqueId(entry.getProfile().uniqueId());
    }
  }

  @Override
  public String toString() {
    return "PlayerInfoRemove{" +
        "entries=" + this.entries +
        '}';
  }
}
