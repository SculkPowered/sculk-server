package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.Objects;
import net.kyori.adventure.sound.SoundStop;

public final class StopSoundPacket implements ClientboundPacket {

  private final SoundStop stop;

  public StopSoundPacket(SoundStop stop) {
    this.stop = stop;
  }

  @Override
  public void encode(Buffer buf) {
    if (this.stop == SoundStop.all()) {
      buf.writeByte((byte) 0);
      return;
    }
    var flags = (byte) 0;
    if (this.stop.source() != null) {
      flags |= 1;
    }
    if (this.stop.sound() != null) {
      flags |= 2;
    }
    buf.writeByte(flags);
    if (this.stop.source() != null) {
      buf.writeVarInt(Objects.requireNonNull(this.stop.source()).ordinal());
    }
    if (this.stop.sound() != null) {
      buf.writeString(Objects.requireNonNull(this.stop.sound()).asString());
    }
  }

  @Override
  public String toString() {
    return "StopSoundPacket{" +
        "stop=" + this.stop +
        '}';
  }
}
