package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class TabListPacket implements ClientboundPacket {

  private final Component header;
  private final Component footer;

  public TabListPacket(final Component header, final Component footer) {
    this.header = header;
    this.footer = footer;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeComponent(this.header)
        .writeComponent(this.footer);
  }

  @Override
  public String toString() {
    return "TabListPacket{" +
        "header=" + this.header +
        ", footer=" + this.footer +
        '}';
  }
}
