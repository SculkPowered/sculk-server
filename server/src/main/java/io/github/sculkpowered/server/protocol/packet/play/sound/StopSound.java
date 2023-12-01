package io.github.sculkpowered.server.protocol.packet.play.sound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.Objects;
import net.kyori.adventure.sound.SoundStop;

public final class StopSound implements Packet {

  private final SoundStop stop;

  public StopSound(SoundStop stop) {
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
    return "StopSound{" +
        "stop=" + this.stop +
        '}';
  }
}
