package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

import java.util.Arrays;

public final class EncryptionResponse implements Packet {

    private byte[] sharedSecret;
    private byte[] verifyToken;
    private long salt;
    private byte[] signature;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.sharedSecret = buf.readByteArray();

        if (version.newerOr(Protocol.Version.MINECRAFT_1_19_3) || buf.readBoolean()) {
            this.verifyToken = buf.readByteArray();
        } else {
            this.salt = buf.readLong();
            this.signature = buf.readByteArray();
        }
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    public byte[] sharedSecret() {
        return this.sharedSecret;
    }

    public byte[] verifyToken() {
        return this.verifyToken;
    }

    public long salt() {
        return this.salt;
    }

    public byte[] signature() {
        return this.signature;
    }

    @Override
    public String toString() {
        return "EncryptionResponse{" +
                "sharedSecret=" + Arrays.toString(this.sharedSecret) +
                ", verifyToken=" + Arrays.toString(this.verifyToken) +
                ", salt=" + this.salt +
                '}';
    }
}
