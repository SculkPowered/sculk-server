package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class AnimatePacket implements ClientboundPacket {

  private final int entityId;
  private final byte animation;

  public AnimatePacket(final int entityId, final byte animation) {
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
    return "AnimatePacket{" +
        "entityId=" + this.entityId +
        ", animation=" + this.animation +
        '}';
  }
}
