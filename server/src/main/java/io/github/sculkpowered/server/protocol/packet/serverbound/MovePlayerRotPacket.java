package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class MovePlayerRotPacket implements ServerboundPacket {

  private float yaw;
  private float pitch;
  private boolean onGround;

  @Override
  public void decode(Buffer buf) {
    this.yaw = buf.readFloat();
    this.pitch = buf.readFloat();
    this.onGround = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 9;
  }

  @Override
  public int maxLength() {
    return this.minLength();
  }

  @Override
  public String toString() {
    return "MovePlayerRotPacket{" +
        "yaw=" + this.yaw +
        ", pitch=" + this.pitch +
        ", onGround=" + this.onGround +
        '}';
  }

  public float yaw() {
    return this.yaw;
  }

  public float pitch() {
    return this.pitch;
  }

  public boolean onGround() {
    return this.onGround;
  }
}
