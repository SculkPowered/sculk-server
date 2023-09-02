package de.bauhd.sculk.protocol.packet.play.title;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class ClearTitles implements Packet {

    private final boolean reset;

    public ClearTitles(final boolean reset) {
        this.reset = reset;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeBoolean(this.reset);
    }

    @Override
    public String toString() {
        return "ClearTitles{" +
                "reset=" + this.reset +
                '}';
    }
}
