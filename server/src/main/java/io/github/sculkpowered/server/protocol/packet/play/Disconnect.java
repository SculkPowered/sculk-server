package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class Disconnect implements Packet {

  private final Component text;

  public Disconnect(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "Disconnect{" +
        "text=" + this.text +
        '}';
  }
}
