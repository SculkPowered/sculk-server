package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class EntityEvent implements Packet {

  private final int entityId;
  private final byte entityStatus;

  public EntityEvent(final int entityId, final byte entityStatus) {
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
    return "EntityEvent{" +
        "entityId=" + this.entityId +
        ", entityStatus=" + this.entityStatus +
        '}';
  }
}
