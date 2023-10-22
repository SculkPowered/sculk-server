package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class UpdateTags implements Packet {

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(0);
  }
}
