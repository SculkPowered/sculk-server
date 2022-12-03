package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class EntityAnimation implements Packet {

    private final int entityId;
    private final byte animation;

    public EntityAnimation(final int entityId, final byte animation) {
        this.entityId = entityId;
        this.animation = animation;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        buf.writeUnsignedByte(this.animation);
    }

    @Override
    public String toString() {
        return "EntityAnimation{" +
                "entityId=" + this.entityId +
                ", animation=" + this.animation +
                '}';
    }
}
