package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class PickupItem implements Packet {

  private final int collectedEntity;
  private final int collector;
  private final int itemCount;

  public PickupItem(final int collectedEntity, final int collector, final int itemCount) {
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
    return "PickupItem{" +
        "collectedEntity=" + this.collectedEntity +
        ", collector=" + this.collector +
        ", itemCount=" + this.itemCount +
        '}';
  }
}
