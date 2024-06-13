package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class SetSubtitleTextPacket implements ClientboundPacket {

  private final Component text;

  public SetSubtitleTextPacket(final Component text) {
    this.text = text;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeComponent(this.text);
  }

  @Override
  public String toString() {
    return "SetSubtitleTextPacket{" +
        "text=" + this.text +
        '}';
  }
}
