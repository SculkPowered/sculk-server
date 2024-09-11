package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class ContainerSetSlotPacket implements ClientboundPacket {

  private final byte windowId;
  private final int stateId;
  private final short slot;
  private final ItemStack slotData;

  public ContainerSetSlotPacket(final byte windowId, final int stateId, final short slot,
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
    return "ContainerSetSlotPacket{" +
        "windowId=" + this.windowId +
        ", stateId=" + this.stateId +
        ", slot=" + this.slot +
        ", slotData=" + this.slotData +
        '}';
  }
}
