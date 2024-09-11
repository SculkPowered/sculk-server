package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.UUID;

public final class ChatSessionUpdatePacket implements ServerboundPacket {

  private UUID sessionId;
  private long expiresAt;
  private byte[] publicKey;
  private byte[] keySignature;

  @Override
  public void decode(Buffer buf) {
    this.sessionId = buf.readUniqueId();
    this.expiresAt = buf.readLong();
    this.publicKey = buf.readByteArray();
    this.keySignature = buf.readByteArray();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ChatSessionUpdatePacket{" +
        "sessionId=" + this.sessionId +
        ", expiresAt=" + this.expiresAt +
        ", publicKey=[" + this.publicKey.length +
        "], keySignature=[" + this.keySignature.length +
        "]}";
  }

  public UUID sessionId() {
    return this.sessionId;
  }

  public long expiresAt() {
    return this.expiresAt;
  }

  public byte[] publicKey() {
    return this.publicKey;
  }

  public byte[] keySignature() {
    return this.keySignature;
  }
}
