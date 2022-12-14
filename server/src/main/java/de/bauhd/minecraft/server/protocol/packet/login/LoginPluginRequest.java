package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class LoginPluginRequest implements Packet {

    private final int messageId;
    private final String identifier;
    private final byte[] data;

    public LoginPluginRequest(final int messageId, final String identifier, final byte[] data) {
        this.messageId = messageId;
        this.identifier = identifier;
        this.data = data;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeVarInt(this.messageId)
                .writeString(this.identifier)
                .writeByteArray(this.data);
    }
}
