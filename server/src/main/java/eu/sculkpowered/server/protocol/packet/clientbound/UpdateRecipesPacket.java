package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class UpdateRecipesPacket implements ClientboundPacket {

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(0);
  }
}
