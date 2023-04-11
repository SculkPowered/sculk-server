package de.bauhd.minecraft.server.protocol.packet.play.command;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class ChatCommand implements Packet {

    private String command;
    private long timestamp;
    private long salt;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf) {
        this.command = buf.readString();
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        final var signatures = buf.readVarInt();
        for (int i = 0; i < signatures; i++) {
            buf.readString();
            buf.readByteArray();
        }
        this.signedPreview = buf.readBoolean();

        // ignore for now
        buf.readAll();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    public String command() {
        return this.command;
    }

    public long timestamp() {
        return this.timestamp;
    }

    public long salt() {
        return this.salt;
    }

    public boolean signedPreview() {
        return this.signedPreview;
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
