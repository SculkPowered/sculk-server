package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readByteArray;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readString;

public final class LoginStart implements Packet {

    private String username;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.username = readString(buf, 16);

        if (version.compare(Protocol.Version.MINECRAFT_1_19)) {
            if (buf.readBoolean()) {
                buf.readLong();
                readByteArray(buf); // public key
                readByteArray(buf); // signature
            }
        }
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "LoginStartPacket{" +
                "username='" + this.username + '\'' +
                '}';
    }
}
