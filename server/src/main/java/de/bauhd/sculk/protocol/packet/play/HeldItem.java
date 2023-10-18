package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

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
