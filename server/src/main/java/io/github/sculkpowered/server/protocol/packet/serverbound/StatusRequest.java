package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class StatusRequest implements ServerboundPacket {

  public static final StatusRequest INSTANCE = new StatusRequest();

  @Override
  public void decode(Buffer buf) {
  }

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
