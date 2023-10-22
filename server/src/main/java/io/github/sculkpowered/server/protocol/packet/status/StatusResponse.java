package io.github.sculkpowered.server.protocol.packet.status;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class StatusResponse implements Packet {

  private final String json;

  public StatusResponse(final String json) {
    this.json = json;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeString(this.json);
  }

  @Override
  public String toString() {
    return "StatusResponse{" +
        "json=" + this.json +
        '}';
  }
}
