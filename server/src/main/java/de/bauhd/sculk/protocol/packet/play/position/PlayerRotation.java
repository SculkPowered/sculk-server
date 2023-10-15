package de.bauhd.sculk.protocol.packet.play.position;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class PlayerRotation implements Packet {

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
    return "PlayerRotation{" +
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
