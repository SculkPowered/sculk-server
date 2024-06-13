package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetExperiencePacket implements ClientboundPacket {

  private final float progress;
  private final int level;
  private final int experience;

  public SetExperiencePacket(final float progress, final int level, final int experience) {
    this.progress = progress;
    this.level = level;
    this.experience = experience;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeFloat(this.progress)
        .writeVarInt(this.level)
        .writeVarInt(this.experience);
  }

  @Override
  public int minLength() {
    return 6;
  }

  @Override
  public String toString() {
    return "SetExperiencePacket{" +
        "progress=" + this.progress +
        ", level=" + this.level +
        ", experience=" + this.experience +
        '}';
  }
}
