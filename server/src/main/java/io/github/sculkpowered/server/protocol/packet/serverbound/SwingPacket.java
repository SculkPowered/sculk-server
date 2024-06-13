package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class SwingPacket implements ServerboundPacket {

  private int hand;

  @Override
  public void decode(Buffer buf) {
    this.hand = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 1;
  }

  @Override
  public int maxLength() {
    return 5;
  }

  @Override
  public String toString() {
    return "SwingPacket{" +
        "hand=" + this.hand +
        '}';
  }

  public int hand() {
    return this.hand;
  }
}
