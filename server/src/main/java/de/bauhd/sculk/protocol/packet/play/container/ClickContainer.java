package de.bauhd.sculk.protocol.packet.play.container;

import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class ClickContainer implements Packet {

  private int windowId;
  private int stateId;
  private short slot;
  private byte button;
  private int mode;
  private Int2ObjectMap<ItemStack> slots;
  private ItemStack carriedItem;

  // something is not 100 percent correct  here
  @Override
  public void decode(Buffer buf) {
    this.windowId = buf.readByte();
    this.stateId = buf.readVarInt();
    this.slot = buf.readShort();
    this.button = buf.readByte();
    this.mode = buf.readVarInt();
    final var slotCount = buf.readVarInt();
    this.slots = new Int2ObjectOpenHashMap<>(slotCount);
    for (var i = 0; i < slotCount; i++) {
      if (buf.readableBytes() == 0) {
        return; // I guess nothing is there
      }
      this.slots.put(buf.readShort(), buf.readItem());
    }
    if (buf.readableBytes() != 0) { // I guess no carried item
      this.carriedItem = buf.readItem();
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ClickContainer{" +
        "windowId=" + this.windowId +
        ", stateId=" + this.stateId +
        ", slot=" + this.slot +
        ", button=" + this.button +
        ", mode=" + this.mode +
        ", slots=" + this.slots +
        ", carriedItem=" + this.carriedItem +
        '}';
  }

  public int windowId() {
    return this.windowId;
  }

  public int stateId() {
    return this.stateId;
  }

  public short slot() {
    return this.slot;
  }

  public byte button() {
    return this.button;
  }

  public int mode() {
    return this.mode;
  }

  public Int2ObjectMap<ItemStack> slots() {
    return this.slots;
  }

  public ItemStack carriedItem() {
    return this.carriedItem;
  }
}
