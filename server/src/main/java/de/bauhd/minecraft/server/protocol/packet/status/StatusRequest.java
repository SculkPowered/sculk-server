package de.bauhd.minecraft.server.protocol.packet.status;

import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

public final class StatusRequest implements Packet {

    public static final StatusRequest INSTANCE = new StatusRequest();

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public int maxLength() {
        return 0;
    }
}
