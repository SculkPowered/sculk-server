package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class ConfirmTeleportation implements Packet {

  private int teleportId;

  @Override
  public void decode(Buffer buf) {
    this.teleportId = buf.readVarInt();
  }

  @Override
  public String toString() {
    return "ConfirmTeleportation{" +
        "teleportId=" + this.teleportId +
        '}';
  }
}
