package io.github.sculkpowered.server.registry;

import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.damage.DamageType;
import io.github.sculkpowered.server.entity.EntityType;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.BlockState;
import io.github.sculkpowered.server.world.dimension.Dimension;

public final class Registries {

  static Registry<Dimension> DIMENSIONS;
  static Registry<Biome> BIOMES;
  static Registry<DamageType> DAMAGE_TYPES;
  static Registry<BlockState> BLOCKS;
  static Registry<Material> MATERIALS;
  static Registry<EntityType> ENTITY_TYPES;

  public static Registry<Dimension> dimensions() {
    return DIMENSIONS;
  }

  public static Registry<Biome> biomes() {
    return BIOMES;
  }

  public static Registry<DamageType> damageTypes() {
    return DAMAGE_TYPES;
  }

  public static Registry<BlockState> blocks() {
    return BLOCKS;
  }

  public static Registry<Material> materials() {
    return MATERIALS;
  }

  public static Registry<EntityType> entityTypes() {
    return ENTITY_TYPES;
  }
}
