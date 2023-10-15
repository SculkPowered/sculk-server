package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.entity.player.PlayerInfoEntry;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
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
