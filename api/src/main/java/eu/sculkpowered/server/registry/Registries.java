package eu.sculkpowered.server.registry;

import eu.sculkpowered.server.attribute.Attribute;
import eu.sculkpowered.server.container.item.Material;
import eu.sculkpowered.server.container.item.data.DataComponentType;
import eu.sculkpowered.server.damage.DamageType;
import eu.sculkpowered.server.enchantment.Enchantment;
import eu.sculkpowered.server.entity.Entity;
import eu.sculkpowered.server.entity.EntityType;
import eu.sculkpowered.server.potion.MobEffectType;
import eu.sculkpowered.server.potion.PotionType;
import eu.sculkpowered.server.world.biome.Biome;
import eu.sculkpowered.server.world.block.BlockState;
import eu.sculkpowered.server.world.dimension.Dimension;
import org.jetbrains.annotations.ApiStatus;

public final class Registries {

  private static Registry.Mutable<Dimension> DIMENSIONS;
  private static Registry.Mutable<Biome> BIOMES;
  private static Registry.Mutable<DamageType> DAMAGE_TYPES;
  private static Registry<BlockState> BLOCKS;
  private static Registry<Material> MATERIALS;
  private static Registry<Enchantment> ENCHANTMENTS;
  private static Registry<PotionType> POTIONS;
  private static Registry<MobEffectType> MOB_EFFECTS;
  private static Registry<DataComponentType<?>> DATA_COMPONENT_TYPES;
  private static Registry<Attribute> ATTRIBUTES;
  private static Registry<EntityType<Entity<?>>> ENTITY_TYPES;

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

  public static Registry<Enchantment> enchantments() {
    return ENCHANTMENTS;
  }

  public static Registry<PotionType> potions() {
    return POTIONS;
  }

  public static Registry<MobEffectType> mobEffects() {
    return MOB_EFFECTS;
  }

  public static Registry<DataComponentType<?>> dataComponentTypes() {
    return DATA_COMPONENT_TYPES;
  }

  public static Registry<Attribute> attributes() {
    return ATTRIBUTES;
  }

  public static Registry<EntityType<Entity<?>>> entityTypes() {
    return ENTITY_TYPES;
  }

  @ApiStatus.Internal
  public static void set(
      final Registry.Mutable<Dimension> dimensions,
      final Registry.Mutable<Biome> biomes,
      final Registry.Mutable<DamageType> damageTypes,
      final Registry<BlockState> blocks,
      final Registry<Material> materials,
      final Registry<Enchantment> enchantments,
      final Registry<PotionType> potions,
      final Registry<MobEffectType> mobEffects,
      final Registry<DataComponentType<?>> dataComponentTypes,
      final Registry<Attribute> attributes,
      final Registry<EntityType<Entity<?>>> entityTypes
  ) {
    DIMENSIONS = dimensions;
    BIOMES = biomes;
    DAMAGE_TYPES = damageTypes;
    BLOCKS = blocks;
    MATERIALS = materials;
    ENCHANTMENTS = enchantments;
    POTIONS = potions;
    MOB_EFFECTS = mobEffects;
    DATA_COMPONENT_TYPES = dataComponentTypes;
    ATTRIBUTES = attributes;
    ENTITY_TYPES = entityTypes;
  }
}
