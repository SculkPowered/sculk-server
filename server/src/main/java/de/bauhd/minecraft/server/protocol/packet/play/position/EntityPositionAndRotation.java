package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class EntityPositionAndRotation implements Packet {

    private final int entityId;
    private final short deltaX;
    private final short deltaY;
    private final short deltaZ;
    private final float yaw;
    private final float pitch;
    private final boolean onGround;

    public EntityPositionAndRotation(final int entityId,
                                     final short deltaX,
                                     final short deltaY,
                                     final short deltaZ,
                                     final float yaw,
                                     final float pitch,
                                     final boolean onGround) {
        this.entityId = entityId;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        buf
                .writeShort(this.deltaX)
                .writeShort(this.deltaY)
                .writeShort(this.deltaZ)
                .writeByte((byte) (this.yaw * 256 / 360))
                .writeByte((byte) (this.pitch * 256 / 360))
                .writeBoolean(this.onGround);
    }
}
