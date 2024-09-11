package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class AcceptTeleportationPacket implements ServerboundPacket {

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
    return "AcceptTeleportationPacket{" +
        "teleportId=" + this.teleportId +
        '}';
  }
}
