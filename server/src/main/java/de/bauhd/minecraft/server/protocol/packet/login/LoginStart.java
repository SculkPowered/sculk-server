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
        this.username = readString(buf, 16);

        if (version.compare(Protocol.Version.MINECRAFT_1_19)) {
            if (buf.readBoolean()) {
                buf.readLong();
                readByteArray(buf); // public key
                readByteArray(buf); // signature
            }
            if (version.compare(Protocol.Version.MINECRAFT_1_19_1)) {
                if (buf.readBoolean()) {
                    this.uniqueId = readUUID(buf);
                }
            }
        }
    }

    @Override
    public void handle(Connection connection) {
        connection.setUsername(this.username);
        connection.play();
        /*final var publicKey = DefaultMinecraftServer.getInstance().getKeyPair().getPublic().getEncoded();
        final var verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        ctx.writeAndFlush(new EncryptionRequest("", publicKey, verifyToken));*/
    }

    @Override
    public String toString() {
        return "LoginStart{" +
                "username='" + this.username + '\'' +
                ", uniqueId=" + this.uniqueId +
                '}';
    }
}
