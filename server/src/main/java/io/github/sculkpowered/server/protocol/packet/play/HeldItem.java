package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

// yes, decode -> short and encode -> byte, is right
public final class HeldItem implements Packet {

  private byte slot;

  public HeldItem(final byte slot) {
    this.slot = slot;
  }

  public HeldItem() {}

  @Override
  public void decode(Buffer buf) {
    this.slot = (byte) buf.readShort();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeByte(this.slot);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "HeldItem{" +
        "slot=" + this.slot +
        '}';
  }

  public byte slot() {
    return this.slot;
  }
}
