package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class ClearTitlesPacket implements ClientboundPacket {

  private final boolean reset;

  public ClearTitlesPacket(final boolean reset) {
    this.reset = reset;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeBoolean(this.reset);
  }

  @Override
  public String toString() {
    return "ClearTitlesPacket{" +
        "reset=" + this.reset +
        '}';
  }
}
