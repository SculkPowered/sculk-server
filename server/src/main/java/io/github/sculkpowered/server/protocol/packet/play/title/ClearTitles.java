package io.github.sculkpowered.server.protocol.packet.play.title;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class ClearTitles implements Packet {

  private final boolean reset;

  public ClearTitles(final boolean reset) {
    this.reset = reset;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeBoolean(this.reset);
  }

  @Override
  public String toString() {
    return "ClearTitles{" +
        "reset=" + this.reset +
        '}';
  }
}
