package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class LoginDisconnectPacket implements ClientboundPacket {

  private final Component reason;

  public LoginDisconnectPacket(final Component reason) {
    this.reason = reason;
  }

  @Override
  public void encode(final Buffer buf) {
    buf.writeComponentJson(this.reason);
  }

  @Override
  public String toString() {
    return "Disconnect{" +
        "reason=" + this.reason +
        '}';
  }
}
