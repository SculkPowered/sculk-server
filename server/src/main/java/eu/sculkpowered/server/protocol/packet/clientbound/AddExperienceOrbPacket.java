package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class AddExperienceOrbPacket implements ClientboundPacket {

  private final int entityId;
  private final double x;
  private final double y;
  private final double z;
  private final short count;

  public AddExperienceOrbPacket(final int entityId, final double x, final double y, final double z,
      final short count) {
    this.entityId = entityId;
    this.x = x;
    this.y = y;
    this.z = z;
    this.count = count;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeDouble(this.x)
        .writeDouble(this.y)
        .writeDouble(this.z)
        .writeShort(this.count);
  }

  @Override
  public String toString() {
    return "AddExperienceOrbPacket{" +
        "entityId=" + this.entityId +
        ", x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", count=" + this.count +
        '}';
  }
}
