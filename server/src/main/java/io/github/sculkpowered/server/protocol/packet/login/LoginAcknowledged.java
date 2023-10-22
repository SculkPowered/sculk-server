package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class LoginAcknowledged implements Packet {

  public static final LoginAcknowledged INSTANCE = new LoginAcknowledged();

  @Override
  public int maxLength() {
    return 0;
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }
}