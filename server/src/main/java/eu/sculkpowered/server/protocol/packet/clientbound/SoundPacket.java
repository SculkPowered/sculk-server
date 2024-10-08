package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import net.kyori.adventure.sound.Sound;

public final class SoundPacket implements ClientboundPacket {

  private final Sound sound;
  private final int x;
  private final int y;
  private final int z;

  public SoundPacket(final Sound sound, final int x, final int y, final int z) {
    this.sound = sound;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeByte((byte) 0)
        .writeString(this.sound.name().asString())
        .writeBoolean(false)
        .writeVarInt(this.sound.source().ordinal())
        .writeInt(this.x)
        .writeInt(this.y)
        .writeInt(this.z)
        .writeFloat(this.sound.volume())
        .writeFloat(this.sound.pitch())
        .writeLong(this.sound.seed().orElse(0));
  }

  @Override
  public String toString() {
    return "SoundPacket{" +
        "sound=" + this.sound +
        ", x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        '}';
  }
}
