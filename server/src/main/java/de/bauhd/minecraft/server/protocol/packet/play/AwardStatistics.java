package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class AwardStatistics implements Packet {

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(0); // send 0 cause statistics are not implemented yet
    }
}
