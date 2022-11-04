package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.Arrays;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readByteArray;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readString;

public final class ChatMessage implements Packet {

    private String message;
    private long timestamp;
    private long salt;
    private byte[] signature;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.message = readString(buf, 256);
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        this.signature = readByteArray(buf);
        this.signedPreview = buf.readBoolean();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "message='" + this.message + '\'' +
                ", timestamp=" + this.timestamp +
                ", salt=" + this.salt +
                ", signature=" + Arrays.toString(this.signature) +
                ", signedPreview=" + this.signedPreview +
                '}';
    }
}
