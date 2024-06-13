package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class CustomQueryPacket implements ClientboundPacket {

  private final int messageId;
  private final String identifier;
  private final byte[] data;

  public CustomQueryPacket(final int messageId, final String identifier, final byte[] data) {
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

  @Override
  public String toString() {
    return "CustomQueryPacket{" +
        "messageId=" + this.messageId +
        ", identifier='" + this.identifier + '\'' +
        ", data=[" + this.data.length + ']' +
        '}';
  }
}
