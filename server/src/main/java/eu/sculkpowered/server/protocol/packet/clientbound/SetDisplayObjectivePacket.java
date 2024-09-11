package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetDisplayObjectivePacket implements ClientboundPacket {

  private final byte position;
  private final String scoreName;

  public SetDisplayObjectivePacket(final byte position, final String scoreName) {
    this.position = position;
    this.scoreName = scoreName;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeByte(this.position)
        .writeString(this.scoreName);
  }

  @Override
  public String toString() {
    return "SetDisplayObjectivePacket{" +
        "position=" + this.position +
        ", scoreName='" + this.scoreName + '\'' +
        '}';
  }
}
