package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.entity.Metadata;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public final class SetEntityDataPacket implements ClientboundPacket {

  private final int entityId;
  private final Int2ObjectMap<Metadata.Entry<?>> metadata;

  public SetEntityDataPacket(final int entityId, final Int2ObjectMap<Metadata.Entry<?>> metadata) {
    this.entityId = entityId;
    this.metadata = metadata;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entityId);
    for (final var entry : this.metadata.int2ObjectEntrySet()) {
      buf
          .writeUnsignedByte(entry.getIntKey())
          .writeVarInt(entry.getValue().type());
      entry.getValue().write(buf);
    }
    buf.writeUnsignedByte(0xFF);
  }

  @Override
  public String toString() {
    return "SetEntityDataPacket{" +
        "entityId=" + this.entityId +
        ", metadata=" + this.metadata +
        '}';
  }
}
