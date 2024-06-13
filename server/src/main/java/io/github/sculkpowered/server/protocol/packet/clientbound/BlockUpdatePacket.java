package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.world.Position;

public final class BlockUpdatePacket implements ClientboundPacket {

  private final double x;
  private final double y;
  private final double z;
  private final int blockId;

  public BlockUpdatePacket(final Position position, final int blockId) {
    this(position.x(), position.y(), position.z(), blockId);
  }

  public BlockUpdatePacket(final double x, final double y, final double z, final int blockId) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.blockId = blockId;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writePosition(this.x, this.y, this.z)
        .writeVarInt(this.blockId);
  }

  @Override
  public String toString() {
    return "BlockUpdatePacket{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", blockId=" + this.blockId +
        '}';
  }
}
