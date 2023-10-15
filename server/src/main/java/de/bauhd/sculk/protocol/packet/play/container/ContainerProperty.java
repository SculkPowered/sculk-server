package de.bauhd.sculk.protocol.packet.play.container;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class ContainerProperty implements Packet {

  private final int windowId;
  private final short property;
  private final short value;

  public ContainerProperty(final int windowId, final short property, final short value) {
    this.windowId = windowId;
    this.property = property;
    this.value = value;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUnsignedByte(this.windowId)
        .writeShort(this.property)
        .writeShort(this.value);
  }

  @Override
  public String toString() {
    return "ContainerProperty{" +
        "windowId=" + this.windowId +
        ", property=" + this.property +
        ", value=" + this.value +
        '}';
  }
}
