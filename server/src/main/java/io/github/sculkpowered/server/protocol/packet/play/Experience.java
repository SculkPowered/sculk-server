package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class Experience implements Packet {

  @Override
  public void encode(Buffer buf) {
    buf
        .writeFloat(0)
        .writeVarInt(0)
        .writeVarInt(0);
  }
}
