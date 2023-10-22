package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class CompressionPacket implements Packet {

  private final int threshold;

  public CompressionPacket(final int threshold) {
    this.threshold = threshold;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.threshold);
  }

  @Override
  public String toString() {
    return "CompressionPacket{" +
        "threshold=" + this.threshold +
        '}';
  }
}
