package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class KeepAlive implements Packet {

    private long timeMillis;

    public KeepAlive(final long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public KeepAlive() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.timeMillis = buf.readLong();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeLong(System.currentTimeMillis());
    }

    public long timeMillis() {
        return this.timeMillis;
    }

    @Override
    public String toString() {
        return "KeepAlive{" +
                "timeMillis=" + this.timeMillis +
                '}';
    }
}
