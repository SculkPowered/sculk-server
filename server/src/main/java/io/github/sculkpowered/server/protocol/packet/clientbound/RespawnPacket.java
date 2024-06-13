package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.entity.player.GameMode;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.world.dimension.Dimension;

public final class RespawnPacket implements ClientboundPacket {

  private final Dimension dimensionType;
  private final String dimensionName;
  private final long hashedSeed;
  private final GameMode gameMode;
  private final byte dataKept;

  public RespawnPacket(final Dimension dimensionType, final String dimensionName, final long hashedSeed,
      final GameMode gameMode, final byte dataKept) {
    this.dimensionType = dimensionType;
    this.dimensionName = dimensionName;
    this.hashedSeed = hashedSeed;
    this.gameMode = gameMode;
    this.dataKept = dataKept;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.dimensionType.id())
        .writeString(this.dimensionType.name())
        .writeLong(this.hashedSeed)
        .writeUnsignedByte(this.gameMode.ordinal())
        .writeByte((byte) -1)
        .writeBoolean(false)
        .writeBoolean(false)
        .writeBoolean(false)
        .writeVarInt(0)
        .writeByte(this.dataKept);
  }

  @Override
  public String toString() {
    return "RespawnPacket{" +
        "dimensionType='" + this.dimensionType + '\'' +
        ", dimensionName='" + this.dimensionName + '\'' +
        ", hashedSeed=" + this.hashedSeed +
        ", gameMode=" + this.gameMode +
        '}';
  }
}
