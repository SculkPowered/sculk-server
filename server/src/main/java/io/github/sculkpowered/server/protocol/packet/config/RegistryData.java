package io.github.sculkpowered.server.protocol.packet.config;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.registry.Registry;

public final class RegistryData implements Packet {

  private final Registry<?> registry;

  public RegistryData(final Registry<?> registry) {
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