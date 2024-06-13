package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class LoginAcknowledgedPacket implements ServerboundPacket {

  public static final LoginAcknowledgedPacket INSTANCE = new LoginAcknowledgedPacket();

  @Override
  public void decode(Buffer buf) {

  }

  @Override
  public int maxLength() {
    return 0;
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "LoginAcknowledgedPacket";
  }
}