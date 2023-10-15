package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

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
