package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.world.Position;

public final class SpawnPosition implements Packet {

  private final Position position;

  public SpawnPosition(final Position position) {
    this.position = position;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writePosition(this.position)
        .writeFloat(this.position.yaw());
  }
}
