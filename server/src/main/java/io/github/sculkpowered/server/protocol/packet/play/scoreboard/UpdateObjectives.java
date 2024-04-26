package io.github.sculkpowered.server.protocol.packet.play.scoreboard;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.scoreboard.NumberFormat;
import net.kyori.adventure.text.Component;

public final class UpdateObjectives implements Packet {

  private final String name;
  private final byte mode;
  private final Component displayName;
  private final int type;
  private final NumberFormat numberFormat;

  private UpdateObjectives(
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
    return "UpdateObjectives{" +
        "name='" + this.name + '\'' +
        ", mode=" + this.mode +
        '}';
  }

  public static UpdateObjectives create(final String name, final Component displayName,
      final byte type, final NumberFormat numberFormat) {
    return new UpdateObjectives(name, (byte) 0, displayName, type, numberFormat);
  }

  public static UpdateObjectives update(final String name, final Component displayName,
      final byte type, final NumberFormat numberFormat) {
    return new UpdateObjectives(name, (byte) 2, displayName, type, numberFormat);
  }

  public static UpdateObjectives remove(final String name) {
    return new UpdateObjectives(name, (byte) 1, null, 0, null);
  }
}
