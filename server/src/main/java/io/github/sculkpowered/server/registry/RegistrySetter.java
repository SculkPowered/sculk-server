package io.github.sculkpowered.server.registry;

import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.damage.DamageTypeRegistry;
import io.github.sculkpowered.server.entity.EntityType;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.BlockRegistry;
import io.github.sculkpowered.server.world.dimension.Dimension;

public final class RegistrySetter {

  public static void set() {
    Registries.DIMENSIONS = new SimpleRegistry<>("minecraft:dimension_type", Dimension.OVERWORLD);
    Registries.BIOMES = new SimpleRegistry<>("minecraft:worldgen/biome", Biome.PLAINS);
    Registries.DAMAGE_TYPES = DamageTypeRegistry.get();
    Registries.BLOCKS = BlockRegistry.addBlocks();
    Registries.MATERIALS = new SimpleRegistry<>("minecraft:item", Material.AIR);
    for (final var material : Material.values()) {
      Registries.MATERIALS.register(material);
    }
    Registries.ENTITY_TYPES = new SimpleRegistry<>("minecraft:entity_type");
    for (final var entityType : EntityType.values()) {
      Registries.ENTITY_TYPES.register(entityType);
    }
  }
}
