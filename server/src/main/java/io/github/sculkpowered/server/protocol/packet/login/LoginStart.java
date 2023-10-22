package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.UUID;

public final class LoginStart implements Packet {

  private String username;
  private UUID uniqueId;

  @Override
  public void decode(Buffer buf) {
    this.username = buf.readString(16);
    this.uniqueId = buf.readUniqueId();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public String username() {
    return this.username;
  }

  public UUID uniqueId() {
    return this.uniqueId;
  }

  @Override
  public String toString() {
    return "LoginStart{" +
        "username='" + this.username + '\'' +
        ", uniqueId=" + this.uniqueId +
        '}';
  }
}
