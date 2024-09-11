package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class ContainerClickPacket implements ServerboundPacket {

  private int windowId;
  private int stateId;
  private short slot;
  private byte button;
  private int mode;
  private Int2ObjectMap<ItemStack> slots;
  private ItemStack carriedItem;

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
      this.slots.put(buf.readShort(), buf.readItem());
    }
    this.carriedItem = buf.readItem();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ContainerClickPacket{" +
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
