package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.world.Position;

public final class SetDefaultSpawnPositionPacket implements ClientboundPacket {

  private final Position position;

  public SetDefaultSpawnPositionPacket(final Position position) {
    this.position = position;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writePosition(this.position)
        .writeFloat(this.position.yaw());
  }

  @Override
  public String toString() {
    return "SetDefaultSpawnPositionPacket{" +
        "position=" + this.position +
        '}';
  }
}
