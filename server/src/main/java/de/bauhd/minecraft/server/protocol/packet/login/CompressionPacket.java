package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class CompressionPacket implements Packet {

    private final int threshold;

    public CompressionPacket(final int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeVarInt(this.threshold);
    }

    @Override
    public String toString() {
        return "CompressionPacket{" +
                "threshold=" + this.threshold +
                '}';
    }
}
