package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetChunkCacheCenterPacket implements ClientboundPacket {

  private final int chunkX;
  private final int chunkZ;

  public SetChunkCacheCenterPacket(final int chunkX, final int chunkZ) {
    this.chunkX = chunkX;
    this.chunkZ = chunkZ;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.chunkX).writeVarInt(this.chunkZ);
  }

  @Override
  public String toString() {
    return "SetChunkCacheCenterPacket{" +
        "chunkX=" + this.chunkX +
        ", chunkZ=" + this.chunkZ +
        '}';
  }
}
