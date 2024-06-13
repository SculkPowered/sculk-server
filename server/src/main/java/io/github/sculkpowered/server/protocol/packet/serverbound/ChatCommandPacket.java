package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class ChatCommandPacket implements ServerboundPacket {

  private String command;

  @Override
  public void decode(Buffer buf) {
    this.command = buf.readString();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public String command() {
    return this.command;
  }

  @Override
  public String toString() {
    return "ChatCommandPacket{" +
        "command='" + this.command + '\'' +
        '}';
  }
}
