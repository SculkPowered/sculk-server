package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.UUID;
import net.kyori.adventure.text.Component;

public final class BossEventPacket implements ClientboundPacket {

  private final UUID uniqueId;
  private final int action;
  private final Component title;
  private final float health;
  private final int color;
  private final int division;
  private final int flags;

  private BossEventPacket(final UUID uniqueId, final int action, final Component title, final float health,
      final int color, final int division, final int flags) {
    this.uniqueId = uniqueId;
    this.action = action;
    this.title = title;
    this.health = health;
    this.color = color;
    this.division = division;
    this.flags = flags;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeUniqueId(this.uniqueId).writeVarInt(this.action);

    if (this.action == 0) {
      buf
          .writeComponent(this.title)
          .writeFloat(this.health)
          .writeVarInt(this.color)
          .writeVarInt(this.division)
          .writeUnsignedByte(this.flags);
    } else if (this.action == 2) {
      buf.writeFloat(this.health);
    } else if (this.action == 3) {
      buf.writeComponent(this.title);
    } else if (this.action == 4) {
      buf.writeVarInt(this.color).writeVarInt(this.division);
    } else if (this.action == 5) {
      buf.writeUnsignedByte(this.flags);
    }
  }

  @Override
  public String toString() {
    return "BossEventPacket{" +
        "uniqueId=" + this.uniqueId +
        ", action=" + this.action +
        ", title=" + this.title +
        ", health=" + this.health +
        ", color=" + this.color +
        ", division=" + this.division +
        ", flags=" + this.flags +
        '}';
  }

  public static BossEventPacket add(final UUID uniqueId, final Component title, final float health,
      final int color, final int division, final int flags) {
    return new BossEventPacket(uniqueId, 0, title, health, color, division, flags);
  }

  public static BossEventPacket remove(final UUID uniqueId) {
    return new BossEventPacket(uniqueId, 1, null, -1, -1, -1, -1);
  }

  public static BossEventPacket update(final UUID uniqueId, final float health) {
    return new BossEventPacket(uniqueId, 2, null, health, -1, -1, -1);
  }

  public static BossEventPacket update(final UUID uniqueId, final Component title) {
    return new BossEventPacket(uniqueId, 3, title, -1, -1, -1, -1);
  }

  public static BossEventPacket update(final UUID uniqueId, final int color, final int division) {
    return new BossEventPacket(uniqueId, 4, null, -1, color, division, -1);
  }

  public static BossEventPacket update(final UUID uniqueId, final int flags) {
    return new BossEventPacket(uniqueId, 3, null, -1, -1, -1, flags);
  }
}
