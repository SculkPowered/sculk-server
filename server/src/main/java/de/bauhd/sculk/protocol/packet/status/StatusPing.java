package de.bauhd.sculk.protocol.packet.status;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class StatusPing implements Packet {

    private long randomId;

    @Override
    public void decode(Buffer buf) {
        this.randomId = buf.readLong();
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeLong(this.randomId);
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public int minLength() {
        return 8;
    }

    @Override
    public int maxLength() {
        return 8;
    }

    @Override
    public String toString() {
        return "StatusPing{" +
                "randomId=" + this.randomId +
                '}';
    }
}
