package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class SetActionBarTextPacket implements ClientboundPacket {

  private final Component text;

  public SetActionBarTextPacket(final Component actionBar) {
    this.text = actionBar;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "SetActionBarTextPacket{" +
        "text=" + this.text +
        '}';
  }
}
