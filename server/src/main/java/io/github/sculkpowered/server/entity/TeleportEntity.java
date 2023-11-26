package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.world.Position;

public final class TeleportEntity implements Packet {

  private final int entityId;
  private final Position position;
  private final boolean onGround;

  public TeleportEntity(final int entityId, final Position position, final boolean onGround) {
    this.entityId = entityId;
    this.position = position;
    this.onGround = onGround;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeDouble(this.position.x())
        .writeDouble(this.position.y())
        .writeDouble(this.position.z())
        .writeAngel(this.position.yaw())
        .writeAngel(this.position.pitch())
        .writeBoolean(this.onGround);

  }

  @Override
  public String toString() {
    return "TeleportEntityPacket{" +
        "entityId=" + this.entityId +
        ", position=" + this.position +
        ", onGround=" + this.onGround +
        '}';
  }
}
