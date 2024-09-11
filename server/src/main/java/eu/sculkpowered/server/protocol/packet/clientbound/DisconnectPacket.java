package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class DisconnectPacket implements ClientboundPacket {

  private final Component text;

  public DisconnectPacket(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "DisconnectPacket{" +
        "text=" + this.text +
        '}';
  }
}
