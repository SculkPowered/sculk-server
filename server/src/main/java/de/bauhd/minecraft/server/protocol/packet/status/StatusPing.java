package de.bauhd.minecraft.server.protocol.packet.status;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

public final class StatusPing implements Packet {

    private long randomId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.randomId = buf.readLong();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeLong(this.randomId);
    }

    @Override
    public boolean handle(Connection connection) {
        connection.sendAndClose(this);
        return false;
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
