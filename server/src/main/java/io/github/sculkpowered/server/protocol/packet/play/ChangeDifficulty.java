package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class ChangeDifficulty implements Packet {

  private final byte difficulty;
  private final boolean locked;

  public ChangeDifficulty(final byte difficulty, final boolean locked) {
    this.difficulty = difficulty;
    this.locked = locked;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUnsignedByte(this.difficulty)
        .writeBoolean(this.locked);
  }
}
