package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class LoginCompressionPacket implements ClientboundPacket {

  private final int threshold;

  public LoginCompressionPacket(final int threshold) {
    this.threshold = threshold;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.threshold);
  }

  @Override
  public String toString() {
    return "LoginCompressionPacket{" +
        "threshold=" + this.threshold +
        '}';
  }
}
