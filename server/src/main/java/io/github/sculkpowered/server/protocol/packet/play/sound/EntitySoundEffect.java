package io.github.sculkpowered.server.protocol.packet.play.sound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import net.kyori.adventure.sound.Sound;

public final class EntitySoundEffect implements Packet {

  private final Sound sound;
  private final int entityId;

  public EntitySoundEffect(final Sound sound, final int entityId) {
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
    return "EntitySoundEffect{" +
        "sound=" + this.sound +
        ", entityId=" + this.entityId +
        '}';
  }
}
