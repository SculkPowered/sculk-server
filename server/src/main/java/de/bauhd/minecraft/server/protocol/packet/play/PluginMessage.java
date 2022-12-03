package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import io.netty5.buffer.DefaultBufferAllocators;

import java.util.Arrays;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

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
        this.identifier = readString(buf, 32);
        this.data = new byte[buf.readableBytes()];
        buf.readBytes(this.data, 0, buf.readableBytes());
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, this.identifier);
        buf.writeBytes(this.data);
    }

    @Override
    public void handle(Connection connection) {
        final var buf = DefaultBufferAllocators.offHeapAllocator().allocate(this.data.length);
        buf.writeBytes(this.data);
        //System.out.println(PacketUtils.readString(buf));
    }

    @Override
    public String toString() {
        return "PluginMessage{" +
                "identifier='" + this.identifier + '\'' +
                ", data=" + Arrays.toString(this.data) +
                '}';
    }
}
