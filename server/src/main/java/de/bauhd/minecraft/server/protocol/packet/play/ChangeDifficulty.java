package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ChangeDifficulty implements Packet {

    private final byte difficulty;
    private final boolean locked;

    public ChangeDifficulty(final byte difficulty, final boolean locked) {
        this.difficulty = difficulty;
        this.locked = locked;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeUnsignedByte(this.difficulty)
                .writeBoolean(this.locked);
    }
}
