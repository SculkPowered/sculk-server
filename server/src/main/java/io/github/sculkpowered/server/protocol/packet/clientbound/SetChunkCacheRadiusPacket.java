package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetChunkCacheRadiusPacket implements ClientboundPacket {

  private final int renderDistance;

  public SetChunkCacheRadiusPacket(final int renderDistance) {
    this.renderDistance = renderDistance;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.renderDistance);
  }

  @Override
  public String toString() {
    return "SetChunkCacheRadiusPacket{" +
        "renderDistance=" + this.renderDistance +
        '}';
  }
}
