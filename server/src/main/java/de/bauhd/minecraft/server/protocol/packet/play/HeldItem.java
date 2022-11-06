package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

// yes, decode -> short and encode -> byte, is right
public final class HeldItem implements Packet {

    private short slot;

    public HeldItem(final short slot) {
        this.slot = slot;
    }

    public HeldItem() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.slot = buf.readShort();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeByte((byte) this.slot);
    }

    public short slot() {
        return this.slot;
    }
}
