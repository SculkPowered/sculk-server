package io.github.sculkpowered.server.registry;

import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.damage.DamageType;
import io.github.sculkpowered.server.enchantment.Enchantment;
import io.github.sculkpowered.server.potion.PotionEffect;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.BlockState;
import io.github.sculkpowered.server.world.dimension.Dimension;
import org.jetbrains.annotations.ApiStatus;

public final class Registries {

  private static Registry.Mutable<Dimension> DIMENSIONS;
  private static Registry.Mutable<Biome> BIOMES;
  private static Registry.Mutable<DamageType> DAMAGE_TYPES;
  private static Registry<BlockState> BLOCKS;
  private static Registry<Material> MATERIALS;
  private static Registry<Enchantment> ENCHANTMENTS;
  private static Registry<PotionEffect> POTION_EFFECTS;

  Registries() {
    throw new AssertionError();
  }

  public static Registry.Mutable<Dimension> dimensions() {
    return DIMENSIONS;
  }

  public static Registry.Mutable<Biome> biomes() {
    return BIOMES;
  }

  public static Registry.Mutable<DamageType> damageTypes() {
    return DAMAGE_TYPES;
  }

  public static Registry<BlockState> blocks() {
    return BLOCKS;
  }

  public static Registry<Material> materials() {
    return MATERIALS;
  }

  public Registry<Enchantment> enchantments() {
    return ENCHANTMENTS;
  }

  public Registry<PotionEffect> potionEffects() {
    return POTION_EFFECTS;
  }

  @ApiStatus.Internal
  public static void set(
      final Registry.Mutable<Dimension> dimensions,
      final Registry.Mutable<Biome> biomes,
      final Registry.Mutable<DamageType> damageTypes,
      final Registry<BlockState> blocks,
      final Registry<Material> materials,
      final Registry<Enchantment> enchantments,
      final Registry<PotionEffect> potionEffects
  ) {
    DIMENSIONS = dimensions;
    BIOMES = biomes;
    DAMAGE_TYPES = damageTypes;
    BLOCKS = blocks;
    MATERIALS = materials;
    ENCHANTMENTS = enchantments;
    POTION_EFFECTS = potionEffects;
  }
}
