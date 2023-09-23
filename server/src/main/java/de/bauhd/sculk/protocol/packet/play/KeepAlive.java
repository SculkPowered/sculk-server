package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

import java.util.function.Supplier;

public final class KeepAlive implements Packet {

    public static final Supplier<KeepAlive> SUPPLIER = KeepAlive::new;

    private long timeMillis;

    public KeepAlive(final long timeMillis) {
        this.timeMillis = timeMillis;
    }

    private KeepAlive() {}

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
