package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class SystemChatMessage implements Packet {

  private final Component data;
  private final boolean overlay;

  public SystemChatMessage(final Component data, final boolean overlay) {
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
    return "SystemChatMessage{" +
        "data=" + this.data +
        ", overlay=" + this.overlay +
        '}';
  }
}
