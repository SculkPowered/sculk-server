package io.github.sculkpowered.server.protocol.packet.shared;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class FinishConfigurationPacket implements ServerboundPacket, ClientboundPacket {

  public static final FinishConfigurationPacket INSTANCE = new FinishConfigurationPacket();

  @Override
  public void encode(Buffer buf) {

  }

  @Override
  public void decode(Buffer buf) {

  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 0;
  }

  @Override
  public int maxLength() {
    return 0;
  }

  @Override
  public String toString() {
    return "FinishConfigurationPacket{}";
  }
}