package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeSlot;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class ContainerSlot implements Packet {

    private byte windowId;
    private int stateId;
    private short slot;
    private Slot slotData;

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeByte(this.windowId);
        writeVarInt(buf, this.stateId);
        buf.writeShort(this.slot);
        writeSlot(buf, this.slotData);
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
