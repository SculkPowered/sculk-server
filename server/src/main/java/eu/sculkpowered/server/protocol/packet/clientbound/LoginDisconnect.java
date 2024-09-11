package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class LoginDisconnect implements ClientboundPacket {

  private final Component text;

  public LoginDisconnect(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponentJson(this.text);
  }

  @Override
  public String toString() {
    return "Disconnect{" +
        "text=" + this.text +
        '}';
  }
}
