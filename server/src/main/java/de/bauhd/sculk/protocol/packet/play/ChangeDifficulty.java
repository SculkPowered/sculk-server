package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

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
