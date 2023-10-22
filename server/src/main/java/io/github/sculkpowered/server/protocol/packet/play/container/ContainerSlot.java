package io.github.sculkpowered.server.protocol.packet.play.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class ContainerSlot implements Packet {

  private final byte windowId;
  private final int stateId;
  private final short slot;
  private final ItemStack slotData;

  public ContainerSlot(final byte windowId, final int stateId, final short slot,
      final ItemStack slotData) {
    this.windowId = windowId;
    this.stateId = stateId;
    this.slot = slot;
    this.slotData = slotData;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeByte(this.windowId)
        .writeVarInt(this.stateId)
        .writeShort(this.slot)
        .writeItem(this.slotData);
  }

  @Override
  public String toString() {
    return "ContainerSlot{" +
        "windowId=" + this.windowId +
        ", stateId=" + this.stateId +
        ", slot=" + this.slot +
        ", slotData=" + this.slotData +
        '}';
  }
}
