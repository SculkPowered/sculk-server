package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.Arrays;

public final class KeyPacket implements ServerboundPacket {

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
    return "KeyPacket{" +
        "sharedSecret=" + Arrays.toString(this.sharedSecret) +
        ", verifyToken=" + Arrays.toString(this.verifyToken) +
        '}';
  }
}
