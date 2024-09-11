package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class UseItemPacket implements ServerboundPacket {

  private int hand;
  private int sequence;

  @Override
  public void decode(Buffer buf) {
    this.hand = buf.readVarInt();
    this.sequence = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "UseItemPacket{" +
        "hand=" + this.hand +
        ", sequence=" + this.sequence +
        '}';
  }

  public int hand() {
    return this.hand;
  }

  public int sequence() {
    return this.sequence;
  }
}
