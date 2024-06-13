package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class MoveEntityPosPacket implements ClientboundPacket {

  private final int entityId;
  private final short deltaX;
  private final short deltaY;
  private final short deltaZ;
  private final boolean onGround;

  public MoveEntityPosPacket(final int entityId, final short deltaX, final short deltaY,
      final short deltaZ, final boolean onGround) {
    this.entityId = entityId;
    this.deltaX = deltaX;
    this.deltaY = deltaY;
    this.deltaZ = deltaZ;
    this.onGround = onGround;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeShort(this.deltaX)
        .writeShort(this.deltaY)
        .writeShort(this.deltaZ)
        .writeBoolean(this.onGround);
  }

  @Override
  public String toString() {
    return "MoveEntityPosPacket{" +
        "entityId=" + this.entityId +
        ", deltaX=" + this.deltaX +
        ", deltaY=" + this.deltaY +
        ", deltaZ=" + this.deltaZ +
        ", onGround=" + this.onGround +
        '}';
  }
}
