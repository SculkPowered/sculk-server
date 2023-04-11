package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class UpdateAttributes implements Packet {

    private final int entityId;

    public UpdateAttributes(final int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.entityId)
                .writeVarInt(0);
    }
}
