package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.registry.Registry;

public final class RegistryDataPacket implements ClientboundPacket {

  private final Registry<?> registry;

  public RegistryDataPacket(final Registry<?> registry) {
    this.registry = registry;
  }

  @Override
  public void encode(Buffer buf) {
    final var entries = this.registry.entries();
    buf
        .writeString(this.registry.type())
        .writeVarInt(entries.size());
    for (final var entry : entries) {
      buf
          .writeString(entry.name())
          .writeBoolean(true)
          .writeBinaryTag(entry.asNBT());
    }
  }
}