package io.github.sculkpowered.server.protocol.packet.play.scoreboard;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class UpdateObjectives implements Packet {

  private final String name;
  private final byte mode;
  private final Component value;
  private final byte type;
  private final Byte numberFormat;

  public UpdateObjectives(final String name, final byte mode, final Component value,
      final byte type, final Byte numberFormat) {
    this.name = name;
    this.mode = mode;
    this.value = value;
    this.type = type;
    this.numberFormat = numberFormat;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeString(this.name)
        .writeByte(this.mode);
    if (this.mode != 1) {
      buf
          .writeComponent(this.value)
          .writeByte(this.type);
      if (this.numberFormat != null) {
        buf.writeByte(this.numberFormat);
      }
    }
  }
}
