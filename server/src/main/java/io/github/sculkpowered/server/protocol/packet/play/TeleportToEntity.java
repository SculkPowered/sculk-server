package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.UUID;

public final class TeleportToEntity implements Packet {

  private UUID target;

  @Override
  public void decode(Buffer buf) {
    this.target = buf.readUniqueId();
  }

  @Override
  public String toString() {
    return "TeleportToEntity{" +
        "target=" + this.target +
        '}';
  }

  public UUID target() {
    return this.target;
  }
}
