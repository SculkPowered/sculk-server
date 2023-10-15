package de.bauhd.sculk.protocol.packet.login;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;
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
