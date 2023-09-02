package de.bauhd.sculk.protocol.packet.login;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

import java.util.UUID;

public final class LoginStart implements Packet {

    private String username;
    private UUID uniqueId;

    @Override
    public void decode(Buffer buf) {
        this.username = buf.readString(16);
        if (buf.readBoolean()) {
            this.uniqueId = buf.readUniqueId();
        }
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    public String username() {
        return this.username;
    }

    public UUID uniqueId() {
        return this.uniqueId;
    }

    @Override
    public String toString() {
        return "LoginStart{" +
                "username='" + this.username + '\'' +
                ", uniqueId=" + this.uniqueId +
                '}';
    }
}
