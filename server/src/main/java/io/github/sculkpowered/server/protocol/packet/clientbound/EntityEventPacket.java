package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class EntityEventPacket implements ClientboundPacket {

  private final int entityId;
  private final byte entityStatus;

  public EntityEventPacket(final int entityId, final byte entityStatus) {
    this.entityId = entityId;
    this.entityStatus = entityStatus;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeInt(this.entityId)
        .writeByte(this.entityStatus);
  }

  @Override
  public String toString() {
    return "EntityEventPacket{" +
        "entityId=" + this.entityId +
        ", entityStatus=" + this.entityStatus +
        '}';
  }
}
