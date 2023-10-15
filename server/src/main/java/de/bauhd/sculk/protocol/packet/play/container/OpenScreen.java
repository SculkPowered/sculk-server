package de.bauhd.sculk.protocol.packet.play.container;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class OpenScreen implements Packet {

  private final int windowId;
  private final int windowType;
  private final Component title;

  public OpenScreen(final int windowId, final int windowType, final Component title) {
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
}
