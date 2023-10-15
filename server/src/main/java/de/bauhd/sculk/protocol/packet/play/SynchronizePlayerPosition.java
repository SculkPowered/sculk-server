package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.world.Position;

public final class SynchronizePlayerPosition implements Packet {

  private static int TELEPORT_ID = 0;

  private final Position position;

  public SynchronizePlayerPosition(final Position position) {
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
    return "SynchronizePlayerPosition{" +
        "position=" + this.position +
        '}';
  }
}
