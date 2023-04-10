package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

import java.util.UUID;

public final class LoginStart implements Packet {

    private String username;
    private UUID uniqueId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.username = buf.readString(16);// + ThreadLocalRandom.current().nextInt(10);

        if (version.newerOr(Protocol.Version.MINECRAFT_1_19)) {
            if (version.older(Protocol.Version.MINECRAFT_1_19_3)) { // removed in minecraft 1.19.3
                if (buf.readBoolean()) {
                    buf.readLong();
                    buf.readByteArray(); // public key
                    buf.readByteArray(); // signature
                }
            }
            if (version.newerOr(Protocol.Version.MINECRAFT_1_19_1)) {
                if (buf.readBoolean()) {
                    this.uniqueId = buf.readUniqueId();
                }
            }
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
