package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.scoreboard.NumberFormat;
import net.kyori.adventure.text.Component;

public final class SetScorePacket implements ClientboundPacket {

  private final String name;
  private final String objective;
  private final int score;
  private final Component displayName;
  private final NumberFormat numberFormat;

  public SetScorePacket(
      final String name,
      final String objective,
      final int score,
      final Component displayName,
      final NumberFormat numberFormat
  ) {
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
    if (this.displayName != null) {
      buf
          .writeBoolean(true)
          .writeComponent(this.displayName);
    } else {
      buf.writeBoolean(false);
    }
    buf.writeNumberFormat(this.numberFormat);
  }

  @Override
  public String toString() {
    return "SetScorePacket{" +
        "name='" + this.name + '\'' +
        ", objective='" + this.objective + '\'' +
        ", score=" + this.score +
        '}';
  }
}
