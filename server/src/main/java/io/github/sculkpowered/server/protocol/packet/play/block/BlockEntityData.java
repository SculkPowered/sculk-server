package io.github.sculkpowered.server.protocol.packet.play.block;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class BlockEntityData implements Packet {

  private final double x;
  private final double y;
  private final double z;
  private final int type;
  private final CompoundBinaryTag nbt;

  public BlockEntityData(final double x, final double y, final double z, final int type,
      final CompoundBinaryTag nbt) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.type = type;
    this.nbt = nbt;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writePosition(this.x, this.y, this.z)
        .writeVarInt(this.type)
        .writeCompoundTag(this.nbt);
  }

  @Override
  public String toString() {
    return "BlockEntityData{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", type=" + this.type +
        ", nbt=" + this.nbt +
        '}';
  }
}
