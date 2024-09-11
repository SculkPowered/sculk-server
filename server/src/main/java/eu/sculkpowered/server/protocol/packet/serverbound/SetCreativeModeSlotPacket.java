package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class SetCreativeModeSlotPacket implements ServerboundPacket {

  private short slot;
  private ItemStack clickedItem;

  @Override
  public void decode(Buffer buf) {
    this.slot = buf.readShort();
    this.clickedItem = buf.readItem();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "SetCreativeModeSlotPacket{" +
        "slot=" + this.slot +
        ", clickedItem=" + this.clickedItem +
        '}';
  }

  public short slot() {
    return this.slot;
  }

  public ItemStack clickedItem() {
    return this.clickedItem;
  }
}
