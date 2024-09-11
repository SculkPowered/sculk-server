package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.world.Position;

public final class PlayerPositionPacket implements ClientboundPacket {

  private static int TELEPORT_ID = 0;

  private final Position position;

  public PlayerPositionPacket(final Position position) {
    this.position = position;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeDouble(this.position.x())
        .writeDouble(this.position.y())
        .writeDouble(this.position.z())
        .writeFloat(this.position.yaw())
        .writeFloat(this.position.pitch())
        .writeByte((byte) 0x00)
        .writeVarInt(TELEPORT_ID++);
  }

  @Override
  public String toString() {
    return "PlayerPositionPacket{" +
        "position=" + this.position +
        '}';
  }
}
