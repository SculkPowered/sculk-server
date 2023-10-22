package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class AwardStatistics implements Packet {

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(0); // send 0 cause statistics are not implemented yet
  }
}
