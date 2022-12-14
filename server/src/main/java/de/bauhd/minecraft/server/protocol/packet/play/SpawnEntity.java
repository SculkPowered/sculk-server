package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.UUID;

public final class SpawnEntity implements Packet {

    private final int entityId;
    private final UUID uniqueId;
    private final int type;

    public SpawnEntity(final int entityId, final UUID uniqueId, final int type) {
        this.entityId = entityId;
        this.uniqueId = uniqueId;
        this.type = type;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeVarInt(this.entityId)
                .writeUniqueId(this.uniqueId)
                .writeVarInt(this.type)
                .writeDouble(18)
                .writeDouble(40)
                .writeDouble(5)
                .writeByte((byte) 0)
                .writeByte((byte) 0)
                .writeByte((byte) 0)
                .writeVarInt(0)
                .writeShort((short) 0)
                .writeShort((short) 0)
                .writeShort((short) 0);
    }
}
