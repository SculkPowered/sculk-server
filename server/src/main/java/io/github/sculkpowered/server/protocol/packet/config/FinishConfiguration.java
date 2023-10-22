package io.github.sculkpowered.server.protocol.packet.config;

import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class FinishConfiguration implements Packet {

  public static final FinishConfiguration INSTANCE = new FinishConfiguration();

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int maxLength() {
    return 0;
  }
}