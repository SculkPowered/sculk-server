package io.github.sculkpowered.server.protocol.packet.play.position;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class HeadRotation implements Packet {

  private final int entityId;
  private final float yaw;

  public HeadRotation(final int entityId, final float yaw) {
    this.entityId = entityId;
    this.yaw = yaw;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeAngel(this.yaw);
  }
}
