package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class RemoveEntities implements Packet {

    private final int[] entityIds;

    public RemoveEntities(final int... entityIds) {
        this.entityIds = entityIds;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(this.entityIds.length);
        for (final var entityId : this.entityIds) {
            buf.writeVarInt(entityId);
        }
    }
}
