package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.entity.meta.EntityMeta;
import eu.sculkpowered.server.registry.Registry;
import eu.sculkpowered.server.registry.SimpleRegistry;
import java.util.function.Function;

public final class EntityTypeRegistry {

  private EntityTypeRegistry() {
    throw new AssertionError();
  }

  @SuppressWarnings("raw")
  public static Registry<EntityType<Entity<?>>> get() {
    final var creator = new Creator();
    final var registry = new SimpleRegistry<EntityType<Entity<?>>>("minecraft:entity_type",
        new EntityType<>("", -1, creator));
    // START
    registry.register(new EntityType<>("minecraft:allay", 0, creator));
    registry.register(new EntityType<>("minecraft:area_effect_cloud", 1, creator));
    registry.register(new EntityType<>("minecraft:armadillo", 2, creator));
    registry.register(new EntityType<>("minecraft:armor_stand", 3, creator));
    registry.register(new EntityType<>("minecraft:arrow", 4, creator));
    registry.register(new EntityType<>("minecraft:axolotl", 5, creator));
    registry.register(new EntityType<>("minecraft:bat", 6, creator));
    registry.register(new EntityType<>("minecraft:bee", 7, creator));
    registry.register(new EntityType<>("minecraft:blaze", 8, creator));
    registry.register(new EntityType<>("minecraft:block_display", 9, creator));
    registry.register(new EntityType<>("minecraft:boat", 10, creator));
    registry.register(new EntityType<>("minecraft:bogged", 11, creator));
    registry.register(new EntityType<>("minecraft:breeze", 12, creator));
    registry.register(new EntityType<>("minecraft:breeze_wind_charge", 13, creator));
    registry.register(new EntityType<>("minecraft:camel", 14, creator));
    registry.register(new EntityType<>("minecraft:cat", 15, creator));
    registry.register(new EntityType<>("minecraft:cave_spider", 16, creator));
    registry.register(new EntityType<>("minecraft:chest_boat", 17, creator));
    registry.register(new EntityType<>("minecraft:chest_minecart", 18, creator));
    registry.register(new EntityType<>("minecraft:chicken", 19, creator));
    registry.register(new EntityType<>("minecraft:cod", 20, creator));
    registry.register(new EntityType<>("minecraft:command_block_minecart", 21, creator));
    registry.register(new EntityType<>("minecraft:cow", 22, creator));
    registry.register(new EntityType<>("minecraft:creeper", 23, creator));
    registry.register(new EntityType<>("minecraft:dolphin", 24, creator));
    registry.register(new EntityType<>("minecraft:donkey", 25, creator));
    registry.register(new EntityType<>("minecraft:dragon_fireball", 26, creator));
    registry.register(new EntityType<>("minecraft:drowned", 27, creator));
    registry.register(new EntityType<>("minecraft:egg", 28, creator));
    registry.register(new EntityType<>("minecraft:elder_guardian", 29, creator));
    registry.register(new EntityType<>("minecraft:end_crystal", 30, creator));
    registry.register(new EntityType<>("minecraft:ender_dragon", 31, creator));
    registry.register(new EntityType<>("minecraft:ender_pearl", 32, creator));
    registry.register(new EntityType<>("minecraft:enderman", 33, creator));
    registry.register(new EntityType<>("minecraft:endermite", 34, creator));
    registry.register(new EntityType<>("minecraft:evoker", 35, creator));
    registry.register(new EntityType<>("minecraft:evoker_fangs", 36, creator));
    registry.register(new EntityType<>("minecraft:experience_bottle", 37, creator));
    registry.register(new EntityType<>("minecraft:experience_orb", 38, creator));
    registry.register(new EntityType<>("minecraft:eye_of_ender", 39, creator));
    registry.register(new EntityType<>("minecraft:falling_block", 40, creator));
    registry.register(new EntityType<>("minecraft:fireball", 62, creator));
    registry.register(new EntityType<>("minecraft:firework_rocket", 41, creator));
    registry.register(new EntityType<>("minecraft:fishing_bobber", 129, creator));
    registry.register(new EntityType<>("minecraft:fox", 42, creator));
    registry.register(new EntityType<>("minecraft:frog", 43, creator));
    registry.register(new EntityType<>("minecraft:furnace_minecart", 44, creator));
    registry.register(new EntityType<>("minecraft:ghast", 45, creator));
    registry.register(new EntityType<>("minecraft:giant", 46, creator));
    registry.register(new EntityType<>("minecraft:glow_item_frame", 47, creator));
    registry.register(new EntityType<>("minecraft:glow_squid", 48, creator));
    registry.register(new EntityType<>("minecraft:goat", 49, creator));
    registry.register(new EntityType<>("minecraft:guardian", 50, creator));
    registry.register(new EntityType<>("minecraft:hoglin", 51, creator));
    registry.register(new EntityType<>("minecraft:hopper_minecart", 52, creator));
    registry.register(new EntityType<>("minecraft:horse", 53, creator));
    registry.register(new EntityType<>("minecraft:husk", 54, creator));
    registry.register(new EntityType<>("minecraft:illusioner", 55, creator));
    registry.register(new EntityType<>("minecraft:interaction", 56, creator));
    registry.register(new EntityType<>("minecraft:iron_golem", 57, creator));
    registry.register(new EntityType<>("minecraft:item", 58, creator));
    registry.register(new EntityType<>("minecraft:item_display", 59, creator));
    registry.register(new EntityType<>("minecraft:item_frame", 60, creator));
    registry.register(new EntityType<>("minecraft:leash_knot", 63, creator));
    registry.register(new EntityType<>("minecraft:lightning_bolt", 64, creator));
    registry.register(new EntityType<>("minecraft:llama", 65, creator));
    registry.register(new EntityType<>("minecraft:llama_spit", 66, creator));
    registry.register(new EntityType<>("minecraft:magma_cube", 67, creator));
    registry.register(new EntityType<>("minecraft:minecart", 69, creator));
    registry.register(new EntityType<>("minecraft:mooshroom", 70, creator));
    registry.register(new EntityType<>("minecraft:mule", 71, creator));
    registry.register(new EntityType<>("minecraft:ocelot", 72, creator));
    registry.register(new EntityType<>("minecraft:ominous_item_spawner", 61, creator));
    registry.register(new EntityType<>("minecraft:painting", 73, creator));
    registry.register(new EntityType<>("minecraft:panda", 74, creator));
    registry.register(new EntityType<>("minecraft:parrot", 75, creator));
    registry.register(new EntityType<>("minecraft:phantom", 76, creator));
    registry.register(new EntityType<>("minecraft:pig", 77, creator));
    registry.register(new EntityType<>("minecraft:piglin", 78, creator));
    registry.register(new EntityType<>("minecraft:piglin_brute", 79, creator));
    registry.register(new EntityType<>("minecraft:pillager", 80, creator));
    registry.register(new EntityType<>("minecraft:polar_bear", 81, creator));
    registry.register(new EntityType<>("minecraft:potion", 82, creator));
    registry.register(new EntityType<>("minecraft:pufferfish", 83, creator));
    registry.register(new EntityType<>("minecraft:rabbit", 84, creator));
    registry.register(new EntityType<>("minecraft:ravager", 85, creator));
    registry.register(new EntityType<>("minecraft:salmon", 86, creator));
    registry.register(new EntityType<>("minecraft:sheep", 87, creator));
    registry.register(new EntityType<>("minecraft:shulker", 88, creator));
    registry.register(new EntityType<>("minecraft:shulker_bullet", 89, creator));
    registry.register(new EntityType<>("minecraft:silverfish", 90, creator));
    registry.register(new EntityType<>("minecraft:skeleton", 91, creator));
    registry.register(new EntityType<>("minecraft:skeleton_horse", 92, creator));
    registry.register(new EntityType<>("minecraft:slime", 93, creator));
    registry.register(new EntityType<>("minecraft:small_fireball", 94, creator));
    registry.register(new EntityType<>("minecraft:sniffer", 95, creator));
    registry.register(new EntityType<>("minecraft:snow_golem", 96, creator));
    registry.register(new EntityType<>("minecraft:snowball", 97, creator));
    registry.register(new EntityType<>("minecraft:spawner_minecart", 98, creator));
    registry.register(new EntityType<>("minecraft:spectral_arrow", 99, creator));
    registry.register(new EntityType<>("minecraft:spider", 100, creator));
    registry.register(new EntityType<>("minecraft:squid", 101, creator));
    registry.register(new EntityType<>("minecraft:stray", 102, creator));
    registry.register(new EntityType<>("minecraft:strider", 103, creator));
    registry.register(new EntityType<>("minecraft:tadpole", 104, creator));
    registry.register(new EntityType<>("minecraft:text_display", 105, creator));
    registry.register(new EntityType<>("minecraft:tnt", 106, creator));
    registry.register(new EntityType<>("minecraft:tnt_minecart", 107, creator));
    registry.register(new EntityType<>("minecraft:trader_llama", 108, creator));
    registry.register(new EntityType<>("minecraft:trident", 109, creator));
    registry.register(new EntityType<>("minecraft:tropical_fish", 110, creator));
    registry.register(new EntityType<>("minecraft:turtle", 111, creator));
    registry.register(new EntityType<>("minecraft:vex", 112, creator));
    registry.register(new EntityType<>("minecraft:villager", 113, creator));
    registry.register(new EntityType<>("minecraft:vindicator", 114, creator));
    registry.register(new EntityType<>("minecraft:wandering_trader", 115, creator));
    registry.register(new EntityType<>("minecraft:warden", 116, creator));
    registry.register(new EntityType<>("minecraft:wind_charge", 117, creator));
    registry.register(new EntityType<>("minecraft:witch", 118, creator));
    registry.register(new EntityType<>("minecraft:wither", 119, creator));
    registry.register(new EntityType<>("minecraft:wither_skeleton", 120, creator));
    registry.register(new EntityType<>("minecraft:wither_skull", 121, creator));
    registry.register(new EntityType<>("minecraft:wolf", 122, creator));
    registry.register(new EntityType<>("minecraft:zoglin", 123, creator));
    registry.register(new EntityType<>("minecraft:zombie", 124, creator));
    registry.register(new EntityType<>("minecraft:zombie_horse", 125, creator));
    registry.register(new EntityType<>("minecraft:zombie_villager", 126, creator));
    registry.register(new EntityType<>("minecraft:zombified_piglin", 127, creator));
    // END
    return registry;
  }

  private static final class Creator<M extends EntityMeta> implements
      Function<EntityType<Entity<M>>, Entity<M>> {

    @Override
    public Entity<M> apply(EntityType<Entity<M>> type) {
      return new AbstractEntity<>(type);
    }
  }
}
