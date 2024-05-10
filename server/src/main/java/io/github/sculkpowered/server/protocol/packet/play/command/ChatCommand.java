package io.github.sculkpowered.server.protocol.packet.play.command;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class ChatCommand implements Packet {

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
    return "ChatCommand{" +
        "command='" + this.command + '\'' +
        '}';
  }
}
