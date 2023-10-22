package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class UpdateAttributes implements Packet {

  private final int entityId;

  public UpdateAttributes(final int entityId) {
    this.entityId = entityId;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeVarInt(0);
  }
}
