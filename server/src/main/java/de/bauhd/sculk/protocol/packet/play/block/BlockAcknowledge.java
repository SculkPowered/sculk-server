package de.bauhd.sculk.protocol.packet.play.block;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class BlockAcknowledge implements Packet {

  private final int sequence;

  public BlockAcknowledge(final int sequence) {
    this.sequence = sequence;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.sequence);
  }

  @Override
  public String toString() {
    return "BlockAcknowledge{" +
        "sequence=" + this.sequence +
        '}';
  }
}
