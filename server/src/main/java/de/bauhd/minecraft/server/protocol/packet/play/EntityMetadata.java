package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class EntityMetadata implements Packet {

    private final int entityId;
    private final int index;
    private final int type;
    private final int value;

    public EntityMetadata(final int entityId, final int index, final int type, final int value) {
        this.entityId = entityId;
        this.index = index;
        this.type = type;
        this.value = value;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        buf.writeUnsignedByte(this.index);
        writeVarInt(buf, this.type);
        writeVarInt(buf, this.value);
        buf
                .writeUnsignedByte(0xFF);
    }
}
