package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class ContainerButtonClickPacket implements ServerboundPacket {

  private byte windowId;
  private byte buttonId;

  @Override
  public void decode(Buffer buf) {
    this.windowId = buf.readByte();
    this.buttonId = buf.readByte();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 2;
  }

  @Override
  public int maxLength() {
    return this.minLength();
  }

  @Override
  public String toString() {
    return "ContainerButtonClickPacket{" +
        "windowId=" + this.windowId +
        ", buttonId=" + this.buttonId +
        '}';
  }

  public byte windowId() {
    return this.windowId;
  }

  public byte buttonId() {
    return this.buttonId;
  }
}
