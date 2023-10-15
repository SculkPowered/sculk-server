package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

// yes, decode -> short and encode -> byte, is right
public final class HeldItem implements Packet {

  private short slot;

  public HeldItem(final short slot) {
    this.slot = slot;
  }

  public HeldItem() {
  }

  @Override
  public void decode(Buffer buf) {
    this.slot = buf.readShort();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeByte((byte) this.slot);
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

  public short slot() {
    return this.slot;
  }
}
