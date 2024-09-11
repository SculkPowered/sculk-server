package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class BlockChangedAckPacket implements ClientboundPacket {

  private final int sequence;

  public BlockChangedAckPacket(final int sequence) {
    this.sequence = sequence;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.sequence);
  }

  @Override
  public String toString() {
    return "BlockChangedAckPacket{" +
        "sequence=" + this.sequence +
        '}';
  }
}
