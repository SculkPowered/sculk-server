package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.Arrays;

public final class EncryptionRequest implements Packet {

  private final String serverId;
  private final byte[] publicKey;
  private final byte[] verifyToken;
  private final boolean authenticate;

  public EncryptionRequest(final String serverId, final byte[] publicKey,
      final byte[] verifyToken, final boolean authenticate) {
    this.serverId = serverId;
    this.publicKey = publicKey;
    this.verifyToken = verifyToken;
    this.authenticate = authenticate;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeString(this.serverId)
        .writeByteArray(this.publicKey)
        .writeByteArray(this.verifyToken)
        .writeBoolean(this.authenticate);
  }

  @Override
  public String toString() {
    return "EncryptionRequest{" +
        "serverId='" + this.serverId + '\'' +
        ", publicKey=" + Arrays.toString(this.publicKey) +
        ", verifyToken=" + Arrays.toString(this.verifyToken) +
        ", authenticate=" + this.authenticate +
        '}';
  }
}
