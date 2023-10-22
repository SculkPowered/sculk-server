package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class RenderDistance implements Packet {

  private final int renderDistance;

  public RenderDistance(final int renderDistance) {
    this.renderDistance = renderDistance;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.renderDistance);
  }
}
