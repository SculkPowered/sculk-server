package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class DisconnectPacket implements ClientboundPacket {

  private final Component reason;

  public DisconnectPacket(final Component reason) {
    this.reason = reason;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.reason);
  }

  @Override
  public String toString() {
    return "DisconnectPacket{" +
        "text=" + this.reason +
        '}';
  }
}
