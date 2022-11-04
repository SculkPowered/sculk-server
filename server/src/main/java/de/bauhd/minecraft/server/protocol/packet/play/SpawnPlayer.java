package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeUUID;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class SpawnPlayer implements Packet {

    private UUID uniqueId;

    public SpawnPlayer(final UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public SpawnPlayer() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, 2);
        writeUUID(buf, this.uniqueId);
        buf.writeDouble(22).writeDouble(40).writeDouble(5).writeByte((byte) 0).writeByte((byte) 0);
    }
}
