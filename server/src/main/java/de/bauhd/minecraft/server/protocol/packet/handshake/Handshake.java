package de.bauhd.minecraft.server.protocol.packet.handshake;

import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

public final class Handshake implements Packet {

    private Protocol.Version version;
    private String serverAddress;
    private int port;
    private int nextStatus;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.version = Protocol.Version.get(PacketUtils.readVarInt(buf));
        this.serverAddress = PacketUtils.readString(buf, 256);
        this.port = buf.readUnsignedShort();
        this.nextStatus = PacketUtils.readVarInt(buf);
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    public Protocol.Version getVersion() {
        return this.version;
    }

    public String getServerAddress() {
        return this.serverAddress;
    }

    public int getPort() {
        return this.port;
    }

    public int getNextStatus() {
        return this.nextStatus;
    }

    @Override
    public String toString() {
        return "HandshakePacket{" +
                "version=" + this.version +
                ", serverAddress='" + this.serverAddress + '\'' +
                ", port=" + this.port +
                ", nextStatus=" + this.nextStatus +
                '}';
    }
}
