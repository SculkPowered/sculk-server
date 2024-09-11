package eu.sculkpowered.server.protocol.packet.shared;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class StatusPingPacket implements ServerboundPacket, ClientboundPacket {

  private long randomId;

  @Override
  public void decode(Buffer buf) {
    this.randomId = buf.readLong();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeLong(this.randomId);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return Long.BYTES;
  }

  @Override
  public int maxLength() {
    return Long.BYTES;
  }

  @Override
  public String toString() {
    return "StatusPing{" +
        "randomId=" + this.randomId +
        '}';
  }
}
