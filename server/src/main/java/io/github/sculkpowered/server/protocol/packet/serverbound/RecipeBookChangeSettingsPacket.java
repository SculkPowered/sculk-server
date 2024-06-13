package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class RecipeBookChangeSettingsPacket implements ServerboundPacket {

  private int bookId;
  private boolean bookOpen;
  private boolean filterActive;

  @Override
  public void decode(Buffer buf) {
    this.bookId = buf.readVarInt();
    this.bookOpen = buf.readBoolean();
    this.filterActive = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public int bookId() {
    return this.bookId;
  }

  public boolean bookOpen() {
    return this.bookOpen;
  }

  public boolean filterActive() {
    return this.filterActive;
  }

  @Override
  public String toString() {
    return "RecipeBookChangeSettingsPacket{" +
        "bookId=" + this.bookId +
        ", bookOpen=" + this.bookOpen +
        ", filterActive=" + this.filterActive +
        '}';
  }
}
