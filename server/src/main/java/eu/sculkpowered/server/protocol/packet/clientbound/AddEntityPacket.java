package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.Vector;
import java.util.UUID;

public final class AddEntityPacket implements ClientboundPacket {

  private final int entityId;
  private final UUID uniqueId;
  private final int type;
  private final Position position;
  private final Vector velocity;

  public AddEntityPacket(final int entityId, final UUID uniqueId, final int type,
      final Position position, final Vector velocity) {
    this.entityId = entityId;
    this.uniqueId = uniqueId;
    this.type = type;
    this.position = position;
    this.velocity = velocity;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeUniqueId(this.uniqueId)
        .writeVarInt(this.type)
        .writeDouble(this.position.x())
        .writeDouble(this.position.y())
        .writeDouble(this.position.z())
        .writeAngel(this.position.yaw())
        .writeAngel(this.position.pitch())
        .writeAngel(this.position.yaw())
        .writeVarInt(0)
        .writeShort((short) Math.min(Math.max(this.velocity.x(), Short.MIN_VALUE), Short.MAX_VALUE))
        .writeShort((short) Math.min(Math.max(this.velocity.y(), Short.MIN_VALUE), Short.MAX_VALUE))
        .writeShort((short) Math.min(Math.max(this.velocity.z(), Short.MIN_VALUE), Short.MAX_VALUE));
  }

  @Override
  public String toString() {
    return "AddEntityPacket{" +
        "entityId=" + this.entityId +
        ", uniqueId=" + this.uniqueId +
        ", type=" + this.type +
        ", position=" + this.position +
        '}';
  }
}
