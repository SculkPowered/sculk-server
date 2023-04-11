package de.bauhd.minecraft.server.protocol.packet.status;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class StatusResponse implements Packet {

    private final String json;

    public StatusResponse(final String json) {
        this.json = json;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeString(this.json);
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "json=" + this.json +
                '}';
    }
}
