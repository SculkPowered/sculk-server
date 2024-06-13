package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.Arrays;

public final class ChatPacket implements ServerboundPacket {

  private String message;
  private long timestamp;
  private long salt;
  private byte[] signature;
  private boolean signedPreview;

  @Override
  public void decode(Buffer buf) {
    this.message = buf.readString(256);
    this.timestamp = buf.readLong();
    this.salt = buf.readLong();
    if (buf.readBoolean()) {
      this.signature = buf.readBytes(256);
    }
    final var messages = buf.readVarInt();
    for (var i = 0; i < messages; i++) {
      buf.readBytes(3); // ignore last seen messages
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public String message() {
    return this.message;
  }

  public long timestamp() {
    return this.timestamp;
  }

  public long salt() {
    return this.salt;
  }

  public byte[] signature() {
    return this.signature;
  }

  public boolean signedPreview() {
    return this.signedPreview;
  }

  @Override
  public String toString() {
    return "ChatMessage{" +
        "message='" + this.message + '\'' +
        ", timestamp=" + this.timestamp +
        ", salt=" + this.salt +
        ", signature=" + Arrays.toString(this.signature) +
        ", signedPreview=" + this.signedPreview +
        '}';
  }
}
