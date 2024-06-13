package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class AwardStatsPacket implements ClientboundPacket {

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(0); // send 0 cause statistics are not implemented yet
  }
}
