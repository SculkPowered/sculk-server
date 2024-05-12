package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.attribute.SculkAttributeValue;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.Collection;

public final class UpdateAttributes implements Packet {

  private final int entityId;
  private final Collection<SculkAttributeValue> attributes;

  public UpdateAttributes(final int entityId, final Collection<SculkAttributeValue> attributes) {
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
    return "UpdateAttributes{" +
        "entityId=" + this.entityId +
        ", attributes=" + this.attributes +
        '}';
  }
}
