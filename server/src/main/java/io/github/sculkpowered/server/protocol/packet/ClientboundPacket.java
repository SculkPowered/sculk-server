package io.github.sculkpowered.server.protocol.packet;

import io.github.sculkpowered.server.protocol.Buffer;

public interface ClientboundPacket {

  void encode(final Buffer buf);

  default int minLength() {
    return 0;
  }
}
