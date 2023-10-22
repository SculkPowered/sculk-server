package io.github.sculkpowered.server.protocol.packet.play.chunk;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class CenterChunk implements Packet {

  private final int chunkX;
  private final int chunkZ;

  public CenterChunk(final int chunkX, final int chunkZ) {
    this.chunkX = chunkX;
    this.chunkZ = chunkZ;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.chunkX).writeVarInt(this.chunkZ);
  }

  @Override
  public String toString() {
    return "CenterChunk{" +
        "chunkX=" + this.chunkX +
        ", chunkZ=" + this.chunkZ +
        '}';
  }
}
