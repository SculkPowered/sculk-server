package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.UUID;

public final class RemoveResourcePack implements Packet {

  private final UUID uniqueId;

  public RemoveResourcePack(final UUID uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public void encode(Buffer buf) {
    if (this.uniqueId != null) {
      buf
          .writeBoolean(true)
          .writeUniqueId(this.uniqueId);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public String toString() {
    return "RemoveResourcePack{" +
        "uniqueId=" + this.uniqueId +
        '}';
  }
}
