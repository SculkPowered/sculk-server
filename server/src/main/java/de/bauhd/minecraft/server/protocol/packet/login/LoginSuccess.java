package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class LoginSuccess implements Packet {

    private final UUID uniqueId;
    private final String username;

    public LoginSuccess(final UUID uniqueId, final String username) {
        this.uniqueId = uniqueId;
        this.username = username;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeUUID(buf, this.uniqueId);
        writeString(buf, this.username);
        writeVarInt(buf, 0);
    }

    @Override
    public String toString() {
        return "LoginSuccess{" +
                "uniqueId=" + this.uniqueId +
                ", username='" + this.username + '\'' +
                '}';
    }
}
