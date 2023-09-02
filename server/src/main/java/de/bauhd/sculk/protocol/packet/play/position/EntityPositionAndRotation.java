package de.bauhd.sculk.protocol.packet.play.position;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

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
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.entityId)
                .writeShort(this.deltaX)
                .writeShort(this.deltaY)
                .writeShort(this.deltaZ)
                .writeAngel(this.yaw)
                .writeAngel(this.pitch)
                .writeBoolean(this.onGround);
    }
}
