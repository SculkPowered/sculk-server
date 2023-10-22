package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.Arrays;

public final class ChatMessage implements Packet {

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
    this.signature = buf.readByteArray();
    this.signedPreview = buf.readBoolean();

    // ignore for now
    buf.readAll();
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
