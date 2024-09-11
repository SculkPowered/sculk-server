package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.world.Position;

public final class BlockDestructionPacket implements ClientboundPacket {

  private final int entityId;
  private final Position position;
  private final byte destroyStage;

  public BlockDestructionPacket(final int entityId, final Position position, final byte destroyStage) {
    this.entityId = entityId;
    this.position = position;
    this.destroyStage = destroyStage;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writePosition(this.position)
        .writeByte(this.destroyStage);
  }

  @Override
  public String toString() {
    return "BlockDestructionPacket{" +
        "entityId=" + this.entityId +
        ", position=" + this.position +
        ", destroyStage=" + this.destroyStage +
        '}';
  }
}
