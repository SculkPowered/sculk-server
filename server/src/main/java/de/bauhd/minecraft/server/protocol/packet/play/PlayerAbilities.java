package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class PlayerAbilities implements Packet {

    private byte flags;
    private Float flyingSpeed;
    private Float viewModifier;

    public PlayerAbilities(final byte flags, final Float flyingSpeed, final Float viewModifier) {
        this.flags = flags;
        this.flyingSpeed = flyingSpeed;
        this.viewModifier = viewModifier;
    }

    public PlayerAbilities() {}

    @Override
    public void encode(Buffer buf) {
        buf
                .writeByte(this.flags)
                .writeFloat(this.flyingSpeed)
                .writeFloat(this.viewModifier);
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
