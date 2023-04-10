package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class LoginPluginResponse implements Packet {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private int messageId;
    private byte[] data = EMPTY_BYTE_ARRAY;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.messageId = buf.readVarInt();
        if (buf.readBoolean()) {
            this.data = buf.readByteArray();
        }
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "LoginPluginResponse{" +
                "messageId=" + this.messageId +
                ", data=byte[" + this.data.length + "]" +
                '}';
    }
}
