package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class Health implements Packet {

    @Override
    public void encode(Buffer buf) {
        buf
                .writeFloat(20)
                .writeVarInt(20)
                .writeFloat(2);
    }
}
