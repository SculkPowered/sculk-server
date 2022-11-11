package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeUUID;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class SpawnPlayer implements Packet {

    private final int entityId;
    private final UUID uniqueId;

    public SpawnPlayer(final int entityId, final UUID uniqueId) {
        this.entityId = entityId;
        this.uniqueId = uniqueId;
    }

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        writeUUID(buf, this.uniqueId);
        buf.writeDouble(22)
                .writeDouble(40)
                .writeDouble(5)
                .writeByte((byte) 0)
                .writeByte((byte) 0);
    }

    @Override
    public String toString() {
        return "SpawnPlayer{" +
                "entityId=" + this.entityId +
                ", uniqueId=" + this.uniqueId +
                '}';
    }
}
