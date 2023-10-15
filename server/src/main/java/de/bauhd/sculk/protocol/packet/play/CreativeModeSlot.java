package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class CreativeModeSlot implements Packet {

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
    return "CreativeModeSlot{" +
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
