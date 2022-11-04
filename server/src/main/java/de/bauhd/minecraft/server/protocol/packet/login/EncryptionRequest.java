package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.Arrays;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeByteArray;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class EncryptionRequest implements Packet {

    private String serverId;
    private byte[] publicKey;
    private byte[] verifyToken;

    public EncryptionRequest(final String serverId, final byte[] publicKey, final byte[] verifyToken) {
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public EncryptionRequest() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, this.serverId);
        writeByteArray(buf, this.publicKey);
        writeByteArray(buf, this.verifyToken);
    }

    @Override
    public String toString() {
        return "EncryptionRequest{" +
                "serverId='" + this.serverId + '\'' +
                ", publicKey=" + Arrays.toString(this.publicKey) +
                ", verifyToken=" + Arrays.toString(this.verifyToken) +
                '}';
    }
}
