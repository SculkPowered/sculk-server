package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class Cooldown implements Packet {

  private final int itemId;
  private final int cooldownTicks;

  public Cooldown(final int itemId, final int cooldownTicks) {
    this.itemId = itemId;
    this.cooldownTicks = cooldownTicks;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.itemId)
        .writeVarInt(this.cooldownTicks);
  }

  @Override
  public String toString() {
    return "Cooldown{" +
        "itemId=" + this.itemId +
        ", cooldownTicks=" + this.cooldownTicks +
        '}';
  }
}
