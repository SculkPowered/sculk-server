package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

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
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.messageId)
        .writeString(this.identifier)
        .writeByteArray(this.data);
  }
}
