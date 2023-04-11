package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class SpawnExperienceOrb implements Packet {

    private final int entityId;
    private final double x;
    private final double y;
    private final double z;
    private final short count;

    public SpawnExperienceOrb(final int entityId, final double x, final double y, final double z, final short count) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.entityId)
                .writeDouble(this.x)
                .writeDouble(this.y)
                .writeDouble(this.z)
                .writeShort(this.count);
    }
}
