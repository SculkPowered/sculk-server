package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

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
    public void decode(Buffer buf, Protocol.Version version) {
        this.identifier = buf.readString(32);
        this.data = buf.readAll();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeString(this.identifier)
                .writeBytes(this.data);
    }

    @Override
    public boolean handle(Connection connection) {
        /*final var buf = DefaultBufferAllocators.offHeapAllocator().allocate(this.data.length);
        buf.writeBytes(this.data);
        System.out.println(PacketUtils.readString(buf))*/
        return false;
    }

    @Override
    public String toString() {
        return "PluginMessage{" +
                "identifier='" + this.identifier + '\'' +
                ", data=" + Arrays.toString(this.data) +
                '}';
    }
}
