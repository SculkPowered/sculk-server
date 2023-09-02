package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class UpdateTags implements Packet {

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(0);
    }
}
