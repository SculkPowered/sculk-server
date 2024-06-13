package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.sound.Sound;

public final class SoundEntityPacket implements ClientboundPacket {

  private final Sound sound;
  private final int entityId;

  public SoundEntityPacket(final Sound sound, final int entityId) {
    this.sound = sound;
    this.entityId = entityId;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeByte((byte) 0)
        .writeString(this.sound.name().asString())
        .writeBoolean(false)
        .writeVarInt(0)
        .writeVarInt(this.entityId)
        .writeFloat(this.sound.volume())
        .writeFloat(this.sound.pitch())
        .writeLong(this.sound.seed().orElse(0));
  }

  @Override
  public String toString() {
    return "SoundEntityPacket{" +
        "sound=" + this.sound +
        ", entityId=" + this.entityId +
        '}';
  }
}
