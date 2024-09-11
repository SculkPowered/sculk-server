package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.registry.Registry;
import net.kyori.adventure.nbt.CompoundBinaryTag;

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
      buf.writeString(entry.key().asString());
      final var nbt = entry.asNBT();
      if (!nbt.equals(CompoundBinaryTag.empty())) {
        buf
            .writeBoolean(true)
            .writeBinaryTag(nbt);
      } else {
        buf.writeBoolean(false);
      }
    }
  }

  @Override
  public String toString() {
    return "RegistryDataPacket{" +
        "registry=" + this.registry.type() +
        '}';
  }
}