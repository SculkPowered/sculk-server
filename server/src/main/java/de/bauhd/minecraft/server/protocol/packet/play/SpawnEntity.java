package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeUUID;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class SpawnEntity implements Packet {

    private static int i = -1;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, 1 + i++);
        writeUUID(buf, UUID.randomUUID());
        writeVarInt(buf, i++);
        buf
                .writeDouble(18 + i)
                .writeDouble(40)
                .writeDouble(5)
                .writeByte((byte) 0)
                .writeByte((byte) 0)
                .writeByte((byte) 0);
        writeVarInt(buf, 0);
        buf
                .writeShort((short) 0)
                .writeShort((short) 0)
                .writeShort((short) 0);
    }
}
