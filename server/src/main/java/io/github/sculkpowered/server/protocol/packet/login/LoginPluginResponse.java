package io.github.sculkpowered.server.protocol.packet.login;

import static io.github.sculkpowered.server.util.Constants.EMPTY_BYTE_ARRAY;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class LoginPluginResponse implements Packet {

  private int messageId;
  private byte[] data = EMPTY_BYTE_ARRAY;

  @Override
  public void decode(Buffer buf) {
    this.messageId = buf.readVarInt();
    if (buf.readBoolean()) {
      this.data = buf.readByteArray();
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "LoginPluginResponse{" +
        "messageId=" + this.messageId +
        ", data=byte[" + this.data.length + "]" +
        '}';
  }
}
