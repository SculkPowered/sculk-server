package io.github.sculkpowered.server.protocol.packet.play.scoreboard;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class UpdateScore implements Packet {

  private final String name;
  private final String objective;
  private final int score;
  private final Component displayName;
  private final Byte numberFormat;

  public UpdateScore(final String name, final String objective,
      final int score, final Component displayName, final Byte numberFormat) {
    this.name = name;
    this.objective = objective;
    this.score = score;
    this.displayName = displayName;
    this.numberFormat = numberFormat;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeString(this.name)
        .writeString(this.objective)
        .writeVarInt(this.score);
    if (this.displayName != Component.empty()) {
      buf.writeComponent(this.displayName);
    }
    if (this.numberFormat != null) {
      buf.writeByte(this.numberFormat);
    }
  }
}
