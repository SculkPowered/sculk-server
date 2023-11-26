package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class ConfirmTeleportation implements Packet {

  private int teleportId;

  @Override
  public void decode(Buffer buf) {
    this.teleportId = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ConfirmTeleportation{" +
        "teleportId=" + this.teleportId +
        '}';
  }
}
