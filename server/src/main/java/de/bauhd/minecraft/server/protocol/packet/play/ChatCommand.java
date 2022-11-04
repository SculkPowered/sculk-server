package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class ChatCommand implements Packet {

    private String command;
    private long timestamp;
    private long salt;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.command = readString(buf);
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        final var signatures = readVarInt(buf);
        for (int i = 0; i < signatures; i++) {
            readString(buf);
            readByteArray(buf);
        }
        this.signedPreview = buf.readBoolean();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public String toString() {
        return "ChatCommand{" +
                "command='" + this.command + '\'' +
                ", timestamp=" + this.timestamp +
                ", salt=" + this.salt +
                ", signedPreview=" + this.signedPreview +
                '}';
    }
}
