package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.attribute.SculkAttributeValue;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.Collection;

public final class UpdateAttributesPacket implements ClientboundPacket {

  private final int entityId;
  private final Collection<SculkAttributeValue> attributes;

  public UpdateAttributesPacket(final int entityId, final Collection<SculkAttributeValue> attributes) {
    this.entityId = entityId;
    this.attributes = attributes;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeVarInt(this.attributes.size());
    for (final var value : this.attributes) {
      buf
          .writeVarInt(value.attribute().id())
          .writeDouble(value.baseValue());
      final var modifiers = value.modifiers();
      buf.writeVarInt(modifiers.size());
      for (final var modifier : modifiers) {
        buf
            .writeUniqueId(modifier.uniqueId())
            .writeDouble(modifier.amount())
            .writeByte((byte) modifier.operation().ordinal());
      }
    }
  }

  @Override
  public String toString() {
    return "UpdateAttributesPacket{" +
        "entityId=" + this.entityId +
        ", attributes=" + this.attributes +
        '}';
  }
}
