package de.bauhd.minecraft.server.protocol.packet.handshake;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
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

    @Override
    public int minLength() {
        return 5; // VarInt 1 + String 1 + Unsigned Short 2 + VarInt 1
    }

    @Override
    public int maxLength() {
        if (DefaultMinecraftServer.BUNGEECORD) {
            return 32770;
        }
        return 1039; // VarInt 5 + String 1027 + Unsigned Short 2 + VarInt 5
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
