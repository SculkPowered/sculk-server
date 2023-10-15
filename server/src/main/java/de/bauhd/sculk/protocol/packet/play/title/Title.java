package de.bauhd.sculk.protocol.packet.play.title;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class Title implements Packet {

  private final Component text;

  public Title(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "Title{" +
        "text=" + this.text +
        '}';
  }
}
