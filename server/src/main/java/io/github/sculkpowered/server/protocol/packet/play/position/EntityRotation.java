package io.github.sculkpowered.server.protocol.packet.play.position;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class EntityRotation implements Packet {

  private final int entityId;
  private final float yaw;
  private final float pitch;
  private final boolean onGround;

  public EntityRotation(final int entityId, final float yaw, final float pitch,
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
}
