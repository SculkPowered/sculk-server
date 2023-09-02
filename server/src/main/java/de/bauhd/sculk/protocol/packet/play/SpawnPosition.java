package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class SpawnPosition implements Packet {

    private final Position position;

    public SpawnPosition(final Position position) {
        this.position = position;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writePosition(this.position)
                .writeFloat(this.position.yaw());
    }
}
