package de.bauhd.minecraft.server.protocol.packet.play.title;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ClearTitles implements Packet {

    private final boolean reset;

    public ClearTitles(final boolean reset) {
        this.reset = reset;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeBoolean(this.reset);
    }

    @Override
    public String toString() {
        return "ClearTitles{" +
                "reset=" + this.reset +
                '}';
    }
}
