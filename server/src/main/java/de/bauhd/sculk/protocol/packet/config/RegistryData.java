package de.bauhd.sculk.protocol.packet.config;

import de.bauhd.sculk.damage.DamageType;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.registry.Registry;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.dimension.Dimension;
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
    buf.writeCompoundTag(CompoundBinaryTag.builder()
        .put(this.biomeRegistry.type(), this.biomeRegistry.asNBT())
        .put(this.dimensionRegistry.type(), this.dimensionRegistry.asNBT())
        .put(this.damageTypeRegistry.type(), this.damageTypeRegistry.asNBT())
        .build());
  }
}