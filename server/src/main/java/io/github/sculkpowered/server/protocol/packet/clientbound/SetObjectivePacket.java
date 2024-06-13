package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.scoreboard.NumberFormat;
import net.kyori.adventure.text.Component;

public final class SetObjectivePacket implements ClientboundPacket {

  private final String name;
  private final byte mode;
  private final Component displayName;
  private final int type;
  private final NumberFormat numberFormat;

  private SetObjectivePacket(
      final String name,
      final byte mode,
      final Component displayName,
      final int type,
      final NumberFormat numberFormat
  ) {
    this.name = name;
    this.mode = mode;
    this.displayName = displayName;
    this.type = type;
    this.numberFormat = numberFormat;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeString(this.name)
        .writeByte(this.mode);
    if (this.mode == 0 || this.mode == 2) {
      buf
          .writeComponent(this.displayName)
          .writeVarInt(this.type)
          .writeNumberFormat(this.numberFormat);
    }
  }

  @Override
  public String toString() {
    return "SetObjectivePacket{" +
        "name='" + this.name + '\'' +
        ", mode=" + this.mode +
        '}';
  }

  public static SetObjectivePacket create(final String name, final Component displayName,
      final byte type, final NumberFormat numberFormat) {
    return new SetObjectivePacket(name, (byte) 0, displayName, type, numberFormat);
  }

  public static SetObjectivePacket update(final String name, final Component displayName,
      final byte type, final NumberFormat numberFormat) {
    return new SetObjectivePacket(name, (byte) 2, displayName, type, numberFormat);
  }

  public static SetObjectivePacket remove(final String name) {
    return new SetObjectivePacket(name, (byte) 1, null, 0, null);
  }
}
