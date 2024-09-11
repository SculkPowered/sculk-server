package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class ResetScorePacket implements ClientboundPacket {

  private final String name;
  private final String objective;

  public ResetScorePacket(final String name, final String objective) {
    this.name = name;
    this.objective = objective;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeString(this.name);
    if (this.objective != null) {
      buf
          .writeBoolean(true)
          .writeString(this.objective);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public String toString() {
    return "ResetScorePacket{" +
        "name='" + this.name + '\'' +
        ", objective='" + this.objective + '\'' +
        '}';
  }
}
