package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class BlockEntityDataPacket implements ClientboundPacket {

  private final double x;
  private final double y;
  private final double z;
  private final int type;
  private final CompoundBinaryTag nbt;

  public BlockEntityDataPacket(final double x, final double y, final double z, final int type,
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
        .writeBinaryTag(this.nbt);
  }

  @Override
  public String toString() {
    return "BlockEntityDataPacket{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", type=" + this.type +
        ", nbt=" + this.nbt +
        '}';
  }
}
