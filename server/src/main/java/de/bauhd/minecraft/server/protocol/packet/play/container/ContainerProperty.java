package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ContainerProperty implements Packet {

    private int windowId;
    private short property;
    private short value;

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeUnsignedByte(this.windowId)
                .writeShort(this.property)
                .writeShort(this.value);
    }

    @Override
    public String toString() {
        return "ContainerProperty{" +
                "windowId=" + this.windowId +
                ", property=" + this.property +
                ", value=" + this.value +
                '}';
    }
}
