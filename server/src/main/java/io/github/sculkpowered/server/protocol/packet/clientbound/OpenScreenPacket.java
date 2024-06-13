package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class OpenScreenPacket implements ClientboundPacket {

  private final int windowId;
  private final int windowType;
  private final Component title;

  public OpenScreenPacket(final int windowId, final int windowType, final Component title) {
    this.windowId = windowId;
    this.windowType = windowType;
    this.title = title;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.windowId)
        .writeVarInt(this.windowType)
        .writeComponent(this.title);
  }

  @Override
  public String toString() {
    return "OpenScreenPacket{" +
        "windowId=" + this.windowId +
        ", windowType=" + this.windowType +
        ", title=" + this.title +
        '}';
  }
}
