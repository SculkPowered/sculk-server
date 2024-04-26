package io.github.sculkpowered.server.protocol.packet.play.scoreboard;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class ResetScore implements Packet {

  private final String name;
  private final String objective;

  public ResetScore(final String name, final String objective) {
    this.name = name;
    this.objective = objective;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeString(this.name);
    if (this.objective != null) {
      buf
          .writeBoolean(true)
          .writeString(this.objective);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public String toString() {
    return "ResetScore{" +
        "name='" + this.name + '\'' +
        ", objective='" + this.objective + '\'' +
        '}';
  }
}
