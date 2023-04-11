package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ContainerSlot implements Packet {

    private final byte windowId;
    private final int stateId;
    private final short slot;
    private final Slot slotData;

    public ContainerSlot(final byte windowId, final int stateId, final short slot, final Slot slotData) {
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
                .writeSlot(this.slotData);
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
