package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class AwardStatsPacket implements ClientboundPacket {

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(0); // send 0 cause statistics are not implemented yet
  }
}
