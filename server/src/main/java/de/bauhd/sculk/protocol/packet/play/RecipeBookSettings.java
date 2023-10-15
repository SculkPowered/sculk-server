package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class RecipeBookSettings implements Packet {

  private int bookId;
  private boolean bookOpen;
  private boolean filterActive;

  @Override
  public void decode(Buffer buf) {
    this.bookId = buf.readVarInt();
    this.bookOpen = buf.readBoolean();
    this.filterActive = buf.readBoolean();
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
    return "RecipeBookSettings{" +
        "bookId=" + this.bookId +
        ", bookOpen=" + this.bookOpen +
        ", filterActive=" + this.filterActive +
        '}';
  }
}
