package de.bauhd.sculk.protocol.packet.play.block;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.world.Position;

public final class BlockDestroyStage implements Packet {

  private final int entityId;
  private final Position position;
  private final byte destroyStage;

  public BlockDestroyStage(final int entityId, final Position position, final byte destroyStage) {
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
    return "BlockDestroyStage{" +
        "entityId=" + this.entityId +
        ", position=" + this.position +
        ", destroyStage=" + this.destroyStage +
        '}';
  }
}
