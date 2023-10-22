package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class Health implements Packet {

  @Override
  public void encode(Buffer buf) {
    buf
        .writeFloat(20)
        .writeVarInt(20)
        .writeFloat(2);
  }
}
