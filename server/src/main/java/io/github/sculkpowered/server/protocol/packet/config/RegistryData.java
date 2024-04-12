package io.github.sculkpowered.server.protocol.packet.config;

import io.github.sculkpowered.server.damage.DamageType;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.dimension.Dimension;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class RegistryData implements Packet {

  private final Registry<Biome> biomeRegistry;
  private final Registry<Dimension> dimensionRegistry;
  private final Registry<DamageType> damageTypeRegistry;

  public RegistryData(final Registry<Biome> biomeRegistry,
      final Registry<Dimension> dimensionRegistry,
      final Registry<DamageType> damageTypeRegistry) {
    this.biomeRegistry = biomeRegistry;
    this.dimensionRegistry = dimensionRegistry;
    this.damageTypeRegistry = damageTypeRegistry;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeBinaryTag(CompoundBinaryTag.builder()
        .put(this.biomeRegistry.type(), this.biomeRegistry.asNBT())
        .put(this.dimensionRegistry.type(), this.dimensionRegistry.asNBT())
        .put(this.damageTypeRegistry.type(), this.damageTypeRegistry.asNBT())
        .build());
  }
}