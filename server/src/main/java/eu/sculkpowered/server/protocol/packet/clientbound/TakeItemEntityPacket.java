package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class TakeItemEntityPacket implements ClientboundPacket {

  private final int collectedEntity;
  private final int collector;
  private final int itemCount;

  public TakeItemEntityPacket(final int collectedEntity, final int collector, final int itemCount) {
    this.collectedEntity = collectedEntity;
    this.collector = collector;
    this.itemCount = itemCount;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.collectedEntity)
        .writeVarInt(this.collector)
        .writeVarInt(this.itemCount);
  }

  @Override
  public String toString() {
    return "TakeItemEntityPacket{" +
        "collectedEntity=" + this.collectedEntity +
        ", collector=" + this.collector +
        ", itemCount=" + this.itemCount +
        '}';
  }
}
