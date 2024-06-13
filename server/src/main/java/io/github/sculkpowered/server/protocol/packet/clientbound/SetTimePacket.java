package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetTimePacket implements ClientboundPacket {

  private final long worldAge;
  private final long time;

  public SetTimePacket(final long worldAge, final long time) {
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
    return "SetTimePacket{" +
        "worldAge=" + this.worldAge +
        ", time=" + this.time +
        '}';
  }
}
