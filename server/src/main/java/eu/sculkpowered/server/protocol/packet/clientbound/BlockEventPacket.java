package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.world.Position;

public final class BlockEventPacket implements ClientboundPacket {

  private final Position position;
  private final byte action;
  private final byte actionParameter;
  private final int blockType;

  public BlockEventPacket(final Position position, final byte action, final byte actionParameter,
      final int blockType) {
    this.position = position;
    this.action = action;
    this.actionParameter = actionParameter;
    this.blockType = blockType;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writePosition(this.position)
        .writeUnsignedByte(this.action)
        .writeUnsignedByte(this.actionParameter)
        .writeVarInt(this.blockType);
  }

  @Override
  public String toString() {
    return "BlockEventPacket{" +
        "position=" + this.position +
        ", action=" + this.action +
        ", actionParameter=" + this.actionParameter +
        ", blockType=" + this.blockType +
        '}';
  }
}
