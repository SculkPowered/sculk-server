package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.Arrays;

public final class HelloPacket implements ClientboundPacket {

  private final String serverId;
  private final byte[] publicKey;
  private final byte[] verifyToken;
  private final boolean authenticate;

  public HelloPacket(final String serverId, final byte[] publicKey,
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
    return "HelloPacket{" +
        "serverId='" + this.serverId + '\'' +
        ", publicKey=" + Arrays.toString(this.publicKey) +
        ", verifyToken=" + Arrays.toString(this.verifyToken) +
        ", authenticate=" + this.authenticate +
        '}';
  }
}
