package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.text.Component;

public final class SystemChatPacket implements ClientboundPacket {

  private final Component data;
  private final boolean overlay;

  public SystemChatPacket(final Component data, final boolean overlay) {
    this.data = data;
    this.overlay = overlay;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeComponent(this.data)
        .writeBoolean(this.overlay);
  }

  @Override
  public String toString() {
    return "SystemChatPacket{" +
        "data=" + this.data +
        ", overlay=" + this.overlay +
        '}';
  }
}
