package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class AwardStatistics implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, 0); // send 0 cause statistics are not implemented yes
    }
}
