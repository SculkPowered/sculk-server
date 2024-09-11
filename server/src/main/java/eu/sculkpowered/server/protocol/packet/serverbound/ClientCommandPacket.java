package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class ClientCommandPacket implements ServerboundPacket {

  private int actionId;

  @Override
  public void decode(Buffer buf) {
    this.actionId = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public int actionId() {
    return this.actionId;
  }

  @Override
  public int minLength() {
    return 1; // VarInt can only be 0 or 1
  }

  @Override
  public int maxLength() {
    return this.minLength();
  }

  @Override
  public String toString() {
    return "ClientCommandPacket{" +
        "actionId=" + this.actionId +
        '}';
  }
}
