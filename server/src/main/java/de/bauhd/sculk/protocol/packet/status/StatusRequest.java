package de.bauhd.sculk.protocol.packet.status;

import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

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
