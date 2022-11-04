package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class SynchronizePlayerPosition implements Packet {

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeDouble(0);
        buf.writeDouble(70);
        buf.writeDouble(0);
        buf.writeFloat(0);
        buf.writeFloat(0);
        buf.writeByte((byte) 0x01);
        writeVarInt(buf, 1);
        buf.writeBoolean(false);
    }
}
