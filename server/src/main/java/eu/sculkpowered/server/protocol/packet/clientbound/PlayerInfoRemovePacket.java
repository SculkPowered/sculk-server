package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.entity.player.PlayerInfoEntry;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.List;

public final class PlayerInfoRemovePacket implements ClientboundPacket {

  private final List<PlayerInfoEntry> entries;

  public PlayerInfoRemovePacket(final List<PlayerInfoEntry> entries) {
    this.entries = entries;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entries.size());
    for (final var entry : this.entries) {
      buf.writeUniqueId(entry.profile().uniqueId());
    }
  }

  @Override
  public String toString() {
    return "PlayerInfoRemovePacket{" +
        "entries=" + this.entries +
        '}';
  }
}
