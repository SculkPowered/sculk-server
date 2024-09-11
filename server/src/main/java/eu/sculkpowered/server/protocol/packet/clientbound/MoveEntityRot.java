package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class MoveEntityRot implements ClientboundPacket {

  private final int entityId;
  private final float yaw;
  private final float pitch;
  private final boolean onGround;

  public MoveEntityRot(final int entityId, final float yaw, final float pitch,
      final boolean onGround) {
    this.entityId = entityId;
    this.yaw = yaw;
    this.pitch = pitch;
    this.onGround = onGround;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeAngel(this.yaw)
        .writeAngel(this.pitch)
        .writeBoolean(this.onGround);
  }

  @Override
  public String toString() {
    return "MoveEntityRot{" +
        "entityId=" + this.entityId +
        ", yaw=" + this.yaw +
        ", pitch=" + this.pitch +
        ", onGround=" + this.onGround +
        '}';
  }
}
