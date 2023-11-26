package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class EntityVelocity implements Packet {

  private final int entityId;
  private final short x;
  private final short y;
  private final short z;

  public EntityVelocity(final int entityId, final short x, final short y, final short z) {
    this.entityId = entityId;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeShort(this.x)
        .writeShort(this.y)
        .writeShort(this.z);
  }

  @Override
  public String toString() {
    return "EntityVelocity{" +
        "entityId=" + this.entityId +
        ", x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        '}';
  }
}
