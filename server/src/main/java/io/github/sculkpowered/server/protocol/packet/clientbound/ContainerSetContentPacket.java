package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.util.ItemList;

public final class ContainerSetContentPacket implements ClientboundPacket {

  private final byte windowId;
  private final int stateId;
  private final ItemList items;
  private final ItemStack carriedItem;

  public ContainerSetContentPacket(
      final byte windowId,
      final int stateId,
      final ItemList items,
      final ItemStack carriedItem
  ) {
    this.windowId = windowId;
    this.stateId = stateId;
    this.items = items;
    this.carriedItem = carriedItem;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUnsignedByte(this.windowId)
        .writeVarInt(this.stateId)
        .writeVarInt(this.items.size());
    for (final var item : this.items) {
      buf.writeItem(item);
    }
    buf.writeItem(this.carriedItem);
  }

  @Override
  public String toString() {
    return "ContainerSetContentPacket{" +
        "windowId=" + this.windowId +
        ", stateId=" + this.stateId +
        ", items=" + this.items +
        ", carriedItem=" + this.carriedItem +
        '}';
  }
}
