package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
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

    @Override
    public void decode(Buffer buf) {
        this.windowId = buf.readUnsignedByte();
        this.stateId = buf.readVarInt();
        this.slot = buf.readShort();
        this.button = buf.readByte();
        this.mode = buf.readVarInt();
        final var slotCount = buf.readVarInt();
        this.slots = new Int2ObjectOpenHashMap<>(slotCount);
        for (int i = 0; i < slotCount; i++) {
            this.slots.put(buf.readShort(), buf.readSlot());
        }
        if (buf.readableBytes() != 0) {
            this.carriedItem = buf.readSlot();
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
}
