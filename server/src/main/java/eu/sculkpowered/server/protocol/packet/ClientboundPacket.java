package eu.sculkpowered.server.protocol.packet;

import eu.sculkpowered.server.protocol.Buffer;

public interface ClientboundPacket {

  void encode(final Buffer buf);

  default int minLength() {
    return 0;
  }
}
