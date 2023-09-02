package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class Experience implements Packet {

    @Override
    public void encode(Buffer buf) {
        buf
                .writeFloat(0)
                .writeVarInt(0)
                .writeVarInt(0);
    }
}
