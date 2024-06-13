package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class PlayerAbilitiesPacket implements ClientboundPacket {

  private byte flags;
  private float flyingSpeed;
  private float viewModifier;

  public PlayerAbilitiesPacket(final byte flags, final Float flyingSpeed, final Float viewModifier) {
    this.flags = flags;
    this.flyingSpeed = flyingSpeed;
    this.viewModifier = viewModifier;
  }

  public PlayerAbilitiesPacket() {
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeByte(this.flags)
        .writeFloat(this.flyingSpeed)
        .writeFloat(this.viewModifier);
  }

  @Override
  public String toString() {
    return "PlayerAbilitiesPacket{" +
        "flags=" + this.flags +
        '}';
  }

  public byte flags() {
    return this.flags;
  }
}
