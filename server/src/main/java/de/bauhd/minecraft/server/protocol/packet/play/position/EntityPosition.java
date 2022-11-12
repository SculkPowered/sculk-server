package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class EntityPosition implements Packet {

    private final int entityId;
    private final short deltaX;
    private final short deltaY;
    private final short deltaZ;
    private final boolean onGround;

    public EntityPosition(final int entityId, final short deltaX, final short deltaY, final short deltaZ, final boolean onGround) {
        this.entityId = entityId;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.onGround = onGround;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        buf
                .writeShort(this.deltaX)
                .writeShort(this.deltaY)
                .writeShort(this.deltaZ)
                .writeBoolean(this.onGround);
    }
}
