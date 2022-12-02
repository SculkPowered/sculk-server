package de.bauhd.minecraft.server.protocol.packet.handshake;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.MinecraftConfig;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.State;
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
    public void handle(Connection connection) {
        connection.set(this.nextStatus == 1 ? State.STATUS : State.LOGIN, this.version);
        connection.setServerAddress(this.serverAddress);
    }

    @Override
    public int minLength() {
        return 5; // VarInt 1 + String 1 + Unsigned Short 2 + VarInt 1
    }

    @Override
    public int maxLength() {
        if (AdvancedMinecraftServer.getInstance().getConfiguration().mode() == MinecraftConfig.Mode.BUNGEECORD) {
            return 32770;
        }
        return 1039; // VarInt 5 + String 1027 + Unsigned Short 2 + VarInt 5
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
