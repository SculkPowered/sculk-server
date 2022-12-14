package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class SpawnPosition implements Packet {

    private final Position position;

    public SpawnPosition(final Position position) {
        this.position = position;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writePosition(this.position)
                .writeFloat(this.position.yaw());
    }
}
