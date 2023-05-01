package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class CreativeModeSlot implements Packet {

    private short slot;
    private ItemStack clickedItem;

    @Override
    public void decode(Buffer buf) {
        this.slot = buf.readShort();
        this.clickedItem = buf.readSlot();
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
