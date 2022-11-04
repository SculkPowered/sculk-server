package de.bauhd.minecraft.server.protocol.packet.play.title;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class ClearTitles implements Packet {

    private boolean reset;

    public ClearTitles(final boolean reset) {
        this.reset = reset;
    }

    public ClearTitles() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public String toString() {
        return "ClearTitles{" +
                "reset=" + this.reset +
                '}';
    }
}
