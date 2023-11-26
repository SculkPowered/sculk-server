package io.github.sculkpowered.server.protocol.packet.play.position;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class PlayerOnGround implements Packet {

  private boolean onGround;

  @Override
  public void decode(Buffer buf) {
    this.onGround = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public boolean onGround() {
    return this.onGround;
  }

  @Override
  public String toString() {
    return "PlayerOnGround{" +
        "onGround=" + this.onGround +
        '}';
  }
}
