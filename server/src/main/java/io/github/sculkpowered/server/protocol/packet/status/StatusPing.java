package io.github.sculkpowered.server.protocol.packet.status;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class StatusPing implements Packet {

  private long randomId;

  @Override
  public void decode(Buffer buf) {
    this.randomId = buf.readLong();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeLong(this.randomId);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return Long.BYTES;
  }

  @Override
  public int maxLength() {
    return Long.BYTES;
  }

  @Override
  public String toString() {
    return "StatusPing{" +
        "randomId=" + this.randomId +
        '}';
  }
}
