package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class LoginStart implements Packet {

    private String username;
    private UUID uniqueId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.username = readString(buf, 16);// + ThreadLocalRandom.current().nextInt(10);

        if (version.newerOr(Protocol.Version.MINECRAFT_1_19)) {
            if (buf.readBoolean()) {
                buf.readLong();
                readByteArray(buf); // public key
                readByteArray(buf); // signature
            }
            if (version.newerOr(Protocol.Version.MINECRAFT_1_19_1)) {
                if (buf.readBoolean()) {
                    this.uniqueId = readUUID(buf);
                }
            }
        }
    }

    @Override
    public boolean handle(Connection connection) {
        connection.setUsername(this.username);
        connection.play(null);
        /*final var publicKey = DefaultMinecraftServer.getInstance().getKeyPair().getPublic().getEncoded();
        final var verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        connection.send(new EncryptionRequest("", publicKey, verifyToken));*/
        return false;
    }

    @Override
    public String toString() {
        return "LoginStart{" +
                "username='" + this.username + '\'' +
                ", uniqueId=" + this.uniqueId +
                '}';
    }
}
