package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

import java.util.Arrays;

public final class PluginMessage implements Packet {

    private String identifier;
    private byte[] data;

    public PluginMessage(final String identifier, final byte[] data) {
        this.identifier = identifier;
        this.data = data;
    }

    public PluginMessage() {}

    @Override
    public void decode(Buffer buf) {
        this.identifier = buf.readString(32);
        this.data = buf.readAll();
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeString(this.identifier)
                .writeBytes(this.data);
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "PluginMessage{" +
                "identifier='" + this.identifier + '\'' +
                ", data=" + Arrays.toString(this.data) +
                '}';
    }

    public String identifier() {
        return this.identifier;
    }

    public byte[] data() {
        return this.data;
    }
}
