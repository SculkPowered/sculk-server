package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.Arrays;

public final class RemoveEntitiesPacket implements ClientboundPacket {

  private final int[] entityIds;

  public RemoveEntitiesPacket(final int... entityIds) {
    this.entityIds = entityIds;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entityIds.length);
    for (final var entityId : this.entityIds) {
      buf.writeVarInt(entityId);
    }
  }

  @Override
  public String toString() {
    return "RemoveEntitiesPacket{" +
        "entityIds=" + Arrays.toString(this.entityIds) +
        '}';
  }
}
