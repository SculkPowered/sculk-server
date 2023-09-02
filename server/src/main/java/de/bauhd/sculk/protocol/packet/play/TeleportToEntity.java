package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

import java.util.UUID;

public final class TeleportToEntity implements Packet {

    private UUID target;

    @Override
    public void decode(Buffer buf) {
        this.target = buf.readUniqueId();
    }

    @Override
    public String toString() {
        return "TeleportToEntity{" +
                "target=" + this.target +
                '}';
    }

    public UUID target() {
        return this.target;
    }
}
