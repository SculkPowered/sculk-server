package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.UUID;

public final class ResourcePackPopPacket implements ClientboundPacket {

  private final UUID uniqueId;

  public ResourcePackPopPacket(final UUID uniqueId) {
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
