package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;

public final class EditBook implements Packet {

  private ItemStack slot;
  private String[] entries;
  private String title;

  @Override
  public void decode(Buffer buf) {
    this.slot = buf.readItem();
    final var count = buf.readVarInt();
    this.entries = new String[count];
    for (int i = 0; i < count; i++) {
      this.entries[i] = buf.readString();
    }
    if (buf.readBoolean()) {
      this.title = buf.readString();
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "EditBook{" +
        "slot=" + this.slot +
        ", entries=String[" + this.entries.length + "]" +
        ", title='" + this.title + '\'' +
        '}';
  }

  public ItemStack slot() {
    return this.slot;
  }

  public String[] entries() {
    return this.entries;
  }

  public String title() {
    return this.title;
  }
}
