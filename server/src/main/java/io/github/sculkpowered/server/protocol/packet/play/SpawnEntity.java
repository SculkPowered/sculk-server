package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.world.Position;
import java.util.UUID;

public final class SpawnEntity implements Packet {

  private final int entityId;
  private final UUID uniqueId;
  private final int type;
  private final Position position;

  public SpawnEntity(final int entityId, final UUID uniqueId, final int type,
      final Position position) {
    this.entityId = entityId;
    this.uniqueId = uniqueId;
    this.type = type;
    this.position = position;
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
        .writeAngel(0)
        .writeVarInt(0)
        .writeShort((short) 0)
        .writeShort((short) 0)
        .writeShort((short) 0);
  }

  @Override
  public String toString() {
    return "SpawnEntity{" +
        "entityId=" + this.entityId +
        ", uniqueId=" + this.uniqueId +
        ", type=" + this.type +
        ", position=" + this.position +
        '}';
  }
}
