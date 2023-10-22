package io.github.sculkpowered.server.protocol.packet.status;

import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class StatusRequest implements Packet {

  public static final StatusRequest INSTANCE = new StatusRequest();

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int maxLength() {
    return 0;
  }

  @Override
  public String toString() {
    return "StatusRequest";
  }
}
