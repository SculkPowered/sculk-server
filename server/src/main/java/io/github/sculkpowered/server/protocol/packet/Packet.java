package io.github.sculkpowered.server.protocol.packet;

import io.github.sculkpowered.server.protocol.Buffer;

public interface Packet {

  default void decode(final Buffer buf) {
  }

  default void encode(final Buffer buf) {
  }

  default boolean handle(final PacketHandler handler) {
    return false;
  }

  default int minLength() {
    return 0;
  }

  default int maxLength() {
    return -1;
  }

}
