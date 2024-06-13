package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class StatusResponsePacket implements ClientboundPacket {

  private final String json;

  public StatusResponsePacket(final String json) {
    this.json = json;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeString(this.json);
  }

  @Override
  public String toString() {
    return "StatusResponsePacket{" +
        "json=" + this.json +
        '}';
  }
}
