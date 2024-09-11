package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class ChangeDifficultyPacket implements ClientboundPacket {

  private final byte difficulty;
  private final boolean locked;

  public ChangeDifficultyPacket(final byte difficulty, final boolean locked) {
    this.difficulty = difficulty;
    this.locked = locked;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUnsignedByte(this.difficulty)
        .writeBoolean(this.locked);
  }

  @Override
  public String toString() {
    return "ChangeDifficultyPacket{" +
        "difficulty=" + this.difficulty +
        ", locked=" + this.locked +
        '}';
  }
}
