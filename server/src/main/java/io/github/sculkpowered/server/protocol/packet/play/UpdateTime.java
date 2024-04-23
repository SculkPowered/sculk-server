package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class UpdateTime implements Packet {

  private final long worldAge;
  private final long time;

  public UpdateTime(final long worldAge, final long time) {
    this.worldAge = worldAge;
    this.time = time;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeLong(this.worldAge)
        .writeLong(this.time);
  }

  @Override
  public String toString() {
    return "UpdateTime{" +
        "worldAge=" + this.worldAge +
        ", time=" + this.time +
        '}';
  }
}
