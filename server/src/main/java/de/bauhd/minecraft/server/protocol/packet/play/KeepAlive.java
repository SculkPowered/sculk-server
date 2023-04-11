package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class KeepAlive implements Packet {

    private long timeMillis;

    public KeepAlive(final long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public KeepAlive() {}

    @Override
    public void decode(Buffer buf) {
        this.timeMillis = buf.readLong();
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeLong(this.timeMillis);
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public int minLength() {
        return 8; // Long
    }

    @Override
    public int maxLength() {
        return 8; // Long
    }

    @Override
    public String toString() {
        return "KeepAlive{" +
                "timeMillis=" + this.timeMillis +
                '}';
    }
}
