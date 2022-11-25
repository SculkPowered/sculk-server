package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class CompressionPacket implements Packet {

    private final int threshold;

    public CompressionPacket(final int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.threshold);
    }

    @Override
    public String toString() {
        return "CompressionPacket{" +
                "threshold=" + this.threshold +
                '}';
    }
}
