package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.UUID;

public final class TeleportToEntityPacket implements ServerboundPacket {

  private UUID target;

  @Override
  public void decode(Buffer buf) {
    this.target = buf.readUniqueId();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "TeleportToEntityPacket{" +
        "target=" + this.target +
        '}';
  }

  public UUID target() {
    return this.target;
  }
}
