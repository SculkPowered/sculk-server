package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class PlayerAbilities implements Packet {

    private byte flags;

    @Override
    public void encode(Buffer buf) {
        buf
                .writeByte(this.flags)
                .writeFloat(0.05F)
                .writeFloat(0.1F);
    }

    @Override
    public void decode(Buffer buf) {
        this.flags = buf.readByte();
    }

    @Override
    public String toString() {
        return "PlayerAbilities{" +
                "flags=" + this.flags +
                '}';
    }

    public byte flags() {
        return this.flags;
    }
}
