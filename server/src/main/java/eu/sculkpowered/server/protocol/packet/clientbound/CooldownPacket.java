package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class CooldownPacket implements ClientboundPacket {

  private final int itemId;
  private final int cooldownTicks;

  public CooldownPacket(final int itemId, final int cooldownTicks) {
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
    return "CooldownPacket{" +
        "itemId=" + this.itemId +
        ", cooldownTicks=" + this.cooldownTicks +
        '}';
  }
}
