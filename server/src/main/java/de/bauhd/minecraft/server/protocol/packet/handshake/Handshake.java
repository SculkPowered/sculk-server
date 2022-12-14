package de.bauhd.minecraft.server.protocol.packet.handshake;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.MinecraftConfig;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.login.Disconnect;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.Protocol.Version.*;

public final class Handshake implements Packet {

    private Protocol.Version version;
    private String serverAddress;
    private int port;
    private State nextStatus;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.version = Protocol.Version.get(buf.readVarInt());
        this.serverAddress = buf.readString(256);
        this.port = buf.readUnsignedShort();
        this.nextStatus = (buf.readVarInt() == 1 ? State.STATUS : State.LOGIN);
    }

    @Override
    public boolean handle(Connection connection) {
        connection.set(this.nextStatus, this.version);
        if (this.nextStatus == State.LOGIN
                && (this.version.older(MINIMUM_VERSION) || this.version.newer(MAXIMUM_VERSION))) {
            connection.send(new Disconnect(Component
                    .translatable("multiplayer.disconnect.outdated_client", Component.text(SUPPORTED_VERSIONS))));
            return true;
        }
        connection.setVersion(this.version);
        connection.setServerAddress(this.serverAddress);
        return false;
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
