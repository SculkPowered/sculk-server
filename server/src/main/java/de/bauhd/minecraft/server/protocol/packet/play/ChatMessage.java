package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

import java.util.Arrays;

public final class ChatMessage implements Packet {

    private String message;
    private long timestamp;
    private long salt;
    private byte[] signature;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.message = buf.readString(256);
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        this.signature = buf.readByteArray();
        this.signedPreview = buf.readBoolean();

        // ignore for now
        buf.buf().skipReadableBytes(buf.buf().readableBytes());
    }

    @Override
    public boolean handle(Connection connection) {
        AdvancedMinecraftServer.getInstance().sendAll(
                new SystemChatMessage(Component.text(connection.player().getUsername() + " - " + this.message), false));
        return false;
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
