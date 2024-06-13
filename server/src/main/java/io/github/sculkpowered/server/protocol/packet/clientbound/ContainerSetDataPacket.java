package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class ContainerSetDataPacket implements ClientboundPacket {

  private final int windowId;
  private final short property;
  private final short value;

  public ContainerSetDataPacket(final int windowId, final short property, final short value) {
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
    return "ContainerSetDataPacket{" +
        "windowId=" + this.windowId +
        ", property=" + this.property +
        ", value=" + this.value +
        '}';
  }
}
