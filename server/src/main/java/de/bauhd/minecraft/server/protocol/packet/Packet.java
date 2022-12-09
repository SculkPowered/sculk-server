package de.bauhd.minecraft.server.protocol.packet;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

public interface Packet {

    default void decode(final Buffer buf, final Protocol.Version version) {}

    default void encode(final Buffer buf, final Protocol.Version version) {}

    default boolean handle(final Connection connection) {
        return false;
    }

    default int minLength() {
        return 0;
    }

    default int maxLength() {
        return -1;
    }

}
