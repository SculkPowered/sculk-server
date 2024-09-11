package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class SetTitleTextPacket implements ClientboundPacket {

  private final Component text;

  public SetTitleTextPacket(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "SetTitleTextPacket{" +
        "text=" + this.text +
        '}';
  }
}
