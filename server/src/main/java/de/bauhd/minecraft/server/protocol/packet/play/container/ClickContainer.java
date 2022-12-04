package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.Arrays;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readSlot;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readVarInt;

public final class ClickContainer implements Packet {

    private int windowId;
    private int stateId;
    private short slot;
    private byte button;
    private int mode;
    private Slot[] slots;
    private Slot carriedItem;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.windowId = buf.readUnsignedByte();
        this.stateId = readVarInt(buf);
        this.slot = buf.readShort();
        this.button = buf.readByte();
        this.mode = readVarInt(buf);
        final var slotCount = readVarInt(buf);
        this.slots = new Slot[slotCount];
        for (int i = 0; i < slotCount; i++) {
            this.slots[buf.readShort()] = readSlot(buf);
        }
        this.carriedItem = readSlot(buf);
    }

    @Override
    public String toString() {
        return "ClickContainer{" +
                "windowId=" + this.windowId +
                ", stateId=" + this.stateId +
                ", slot=" + this.slot +
                ", button=" + this.button +
                ", mode=" + this.mode +
                ", slots=" + Arrays.toString(this.slots) +
                ", carriedItem=" + this.carriedItem +
                '}';
    }
}
