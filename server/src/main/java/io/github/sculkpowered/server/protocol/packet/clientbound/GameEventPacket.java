package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class GameEventPacket implements ClientboundPacket {

  private final int event;
  private final float value;

  public GameEventPacket(final int event, final float value) {
    this.event = event;
    this.value = value;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUnsignedByte(this.event)
        .writeFloat(this.value);
  }

  @Override
  public String toString() {
    return "GameEventPacket{" +
        "event=" + this.event +
        ", value=" + this.value +
        '}';
  }
}
