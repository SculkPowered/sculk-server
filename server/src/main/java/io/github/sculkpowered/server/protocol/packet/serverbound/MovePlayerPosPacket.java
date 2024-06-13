package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class MovePlayerPosPacket implements ServerboundPacket {

  private double x;
  private double y;
  private double z;
  private boolean onGround;

  @Override
  public void decode(Buffer buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
    this.onGround = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 25;
  }

  @Override
  public int maxLength() {
    return this.minLength();
  }

  @Override
  public String toString() {
    return "MovePlayerPosPacket{" +
        "x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", onGround=" + this.onGround +
        '}';
  }

  public double x() {
    return this.x;
  }

  public double y() {
    return this.y;
  }

  public double z() {
    return this.z;
  }

  public boolean onGround() {
    return this.onGround;
  }
}
