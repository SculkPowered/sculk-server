package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.Arrays;

public final class EncryptionResponse implements Packet {

  private byte[] sharedSecret;
  private byte[] verifyToken;

  @Override
  public void decode(Buffer buf) {
    this.sharedSecret = buf.readByteArray();
    this.verifyToken = buf.readByteArray();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public byte[] sharedSecret() {
    return this.sharedSecret;
  }

  public byte[] verifyToken() {
    return this.verifyToken;
  }

  @Override
  public String toString() {
    return "EncryptionResponse{" +
        "sharedSecret=" + Arrays.toString(this.sharedSecret) +
        ", verifyToken=" + Arrays.toString(this.verifyToken) +
        '}';
  }
}
