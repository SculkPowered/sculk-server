package io.github.sculkpowered.server.protocol.packet.play.block;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class BlockChange implements Packet {

  private final int sequenceId;

  public BlockChange(final int sequenceId) {
    this.sequenceId = sequenceId;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.sequenceId);
  }

  @Override
  public String toString() {
    return "BlockChange{" +
        "sequenceId=" + this.sequenceId +
        '}';
  }
}
