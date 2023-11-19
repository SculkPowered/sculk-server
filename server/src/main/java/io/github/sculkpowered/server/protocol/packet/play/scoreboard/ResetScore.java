package io.github.sculkpowered.server.protocol.packet.play.scoreboard;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class ResetScore implements Packet {

  private final String entity;
  private final String objective;

  public ResetScore(final String entity, final String objective) {
    this.entity = entity;
    this.objective = objective;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeString(this.entity);
    if (this.objective != null) {
      buf.writeString(this.objective);
    }
  }
}
