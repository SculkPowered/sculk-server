package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetHealthPacket implements ClientboundPacket {

  private final float health;
  private final int food;
  private final float saturation;

  public SetHealthPacket(final float health, final int food, final float saturation) {
    this.health = health;
    this.food = food;
    this.saturation = saturation;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeFloat(this.health)
        .writeVarInt(this.food)
        .writeFloat(this.saturation);
  }

  @Override
  public int minLength() {
    return 9;
  }

  @Override
  public String toString() {
    return "SetHealthPacket{" +
        "health=" + this.health +
        ", food=" + this.food +
        ", saturation=" + this.saturation +
        '}';
  }
}
