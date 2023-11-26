package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.attribute.SculkAttributeValue;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class UpdateAttributes implements Packet {

  private final int entityId;
  private final SculkAttributeValue value;

  public UpdateAttributes(final int entityId, final SculkAttributeValue value) {
    this.entityId = entityId;
    this.value = value;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.entityId)
        .writeVarInt(1)
        .writeString(this.value.attribute().key())
        .writeDouble(this.value.baseValue());
    final var modifiers = this.value.modifiers();
    buf.writeVarInt(modifiers.size());
    for (final var modifier : modifiers) {
      buf
          .writeUniqueId(modifier.uniqueId())
          .writeDouble(modifier.amount())
          .writeByte((byte) modifier.operation().ordinal());
    }
  }
}
