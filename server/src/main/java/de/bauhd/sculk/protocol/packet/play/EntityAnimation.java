package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class EntityAnimation implements Packet {

  private final int entityId;
  private final byte animation;

  public EntityAnimation(final int entityId, final byte animation) {
    this.entityId = entityId;
    this.animation = animation;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeUnsignedByte(this.animation);
  }

  @Override
  public String toString() {
    return "EntityAnimation{" +
        "entityId=" + this.entityId +
        ", animation=" + this.animation +
        '}';
  }
}
