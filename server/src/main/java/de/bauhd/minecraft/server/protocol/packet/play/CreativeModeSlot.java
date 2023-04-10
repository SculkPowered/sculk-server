package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class CreativeModeSlot implements Packet {

    private short slot;
    private Slot clickedItem;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
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

    public Slot clickedItem() {
        return this.clickedItem;
    }
}
