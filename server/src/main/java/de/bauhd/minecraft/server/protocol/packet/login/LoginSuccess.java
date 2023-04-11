package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.UUID;

public final class LoginSuccess implements Packet {

    private final UUID uniqueId;
    private final String username;

    public LoginSuccess(final UUID uniqueId, final String username) {
        this.uniqueId = uniqueId;
        this.username = username;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeUniqueId(this.uniqueId)
                .writeString(this.username)
                .writeVarInt(0);
    }

    @Override
    public String toString() {
        return "LoginSuccess{" +
                "uniqueId=" + this.uniqueId +
                ", username='" + this.username + '\'' +
                '}';
    }
}
