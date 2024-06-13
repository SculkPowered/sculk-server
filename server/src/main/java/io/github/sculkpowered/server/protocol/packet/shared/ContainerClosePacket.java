package io.github.sculkpowered.server.protocol.packet.shared;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class ContainerClosePacket implements ServerboundPacket, ClientboundPacket {

  private int id;

  public ContainerClosePacket(final int id) {
    this.id = id;
  }

  public ContainerClosePacket() {
  }

  @Override
  public void decode(Buffer buf) {
    this.id = buf.readUnsignedByte();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeUnsignedByte(this.id);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 1;
  }

  @Override
  public int maxLength() {
    return this.minLength();
  }

  @Override
  public String toString() {
    return "ContainerClosePacket{" +
        "id=" + this.id +
        '}';
  }
}
