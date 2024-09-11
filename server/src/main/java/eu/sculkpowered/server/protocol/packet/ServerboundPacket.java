package eu.sculkpowered.server.protocol.packet;

import eu.sculkpowered.server.protocol.Buffer;

public interface ServerboundPacket {

  void decode(final Buffer buf);

  boolean handle(final PacketHandler handler);

  default int minLength() {
    return 0;
  }

  default int maxLength() {
    return -1;
  }
}
