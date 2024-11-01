package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.world.Position;

public final class TeleportEntityPacket implements ClientboundPacket {

  private final int entityId;
  private final Position position;
  private final boolean onGround;

  public TeleportEntityPacket(final int entityId, final Position position, final boolean onGround) {
    this.entityId = entityId;
    this.position = position;
    this.onGround = onGround;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeDouble(this.position.x())
        .writeDouble(this.position.y())
        .writeDouble(this.position.z())
        .writeAngel(this.position.yaw())
        .writeAngel(this.position.pitch())
        .writeBoolean(this.onGround);

  }

  @Override
  public String toString() {
    return "TeleportEntityPacket{" +
        "entityId=" + this.entityId +
        ", position=" + this.position +
        ", onGround=" + this.onGround +
        '}';
  }
}
