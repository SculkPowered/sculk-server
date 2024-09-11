package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetTitlesAnimationPacket implements ClientboundPacket {

  private final int fadeIn;
  private final int stay;
  private final int fadeOut;

  public SetTitlesAnimationPacket(final int fadeIn, final int stay, final int fadeOut) {
    this.fadeIn = fadeIn;
    this.stay = stay;
    this.fadeOut = fadeOut;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeInt(this.fadeIn)
        .writeInt(this.stay)
        .writeInt(this.fadeOut);
  }

  @Override
  public String toString() {
    return "SetTitlesAnimationPacket{" +
        "fadeIn=" + this.fadeIn +
        ", stay=" + this.stay +
        ", fadeOut=" + this.fadeOut +
        '}';
  }
}
