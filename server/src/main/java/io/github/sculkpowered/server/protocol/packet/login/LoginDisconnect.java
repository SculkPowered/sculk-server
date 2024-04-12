package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class LoginDisconnect implements Packet {

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
