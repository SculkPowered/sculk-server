package de.bauhd.minecraft.server.protocol.packet;

import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

public interface Packet {

    void decode(final Buffer buf, final Protocol.Version version);

    void encode(final Buffer buf, final Protocol.Version version);

    default int minLength() {
        return 0;
    }

    default int maxLength() {
        return -1;
    }

}
