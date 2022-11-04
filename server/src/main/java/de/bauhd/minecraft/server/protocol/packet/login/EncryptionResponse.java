package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.Arrays;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readByteArray;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readVarInt;

public final class EncryptionResponse implements Packet {

    private byte[] sharedSecret;
    private byte[] verifyToken;
    private long salt;
    private byte[] signature;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        final var sharedSecretLength = readVarInt(buf);
        this.sharedSecret = new byte[sharedSecretLength];
        buf.readBytes(this.sharedSecret, 0, sharedSecretLength);

        if (buf.readBoolean()) {
            this.verifyToken = readByteArray(buf);
            this.salt = buf.readLong();
            this.signature = readByteArray(buf);
        }
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public String toString() {
        return "EncryptionResponse{" +
                "sharedSecret=" + Arrays.toString(this.sharedSecret) +
                ", verifyToken=" + Arrays.toString(this.verifyToken) +
                ", salt=" + this.salt +
                ", signature=" + Arrays.toString(this.signature) +
                '}';
    }
}
