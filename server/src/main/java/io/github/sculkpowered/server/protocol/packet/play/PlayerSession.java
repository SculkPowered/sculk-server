package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.UUID;

public final class PlayerSession implements Packet {

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
