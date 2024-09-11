package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class RotateHeadPacket implements ClientboundPacket {

  private final int entityId;
  private final float yaw;

  public RotateHeadPacket(final int entityId, final float yaw) {
    this.entityId = entityId;
    this.yaw = yaw;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeAngel(this.yaw);
  }

  @Override
  public String toString() {
    return "RotateHeadPacket{" +
        "entityId=" + this.entityId +
        ", yaw=" + this.yaw +
        '}';
  }
}
