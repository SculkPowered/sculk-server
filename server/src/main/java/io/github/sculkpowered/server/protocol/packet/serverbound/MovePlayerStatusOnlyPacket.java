package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class MovePlayerStatusOnlyPacket implements ServerboundPacket {

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
    return "MovePlayerStatusOnlyPacket{" +
        "onGround=" + this.onGround +
        '}';
  }
}
