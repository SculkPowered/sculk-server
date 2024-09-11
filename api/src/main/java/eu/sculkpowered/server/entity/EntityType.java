package eu.sculkpowered.server.entity;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * All entity types possible.
 */
public enum EntityType {

  // START
  ALLAY("allay"),
  AREA_EFFECT_CLOUD("area_effect_cloud"),
  ARMADILLO("armadillo"),
  ARMOR_STAND("armor_stand"),
  ARROW("arrow"),
  AXOLOTL("axolotl"),
  BAT("bat"),
  BEE("bee"),
  BLAZE("blaze"),
  BLOCK_DISPLAY("block_display"),
  BOAT("boat"),
  BOGGED("bogged"),
  BREEZE("breeze"),
  BREEZE_WIND_CHARGE("breeze_wind_charge"),
  CAMEL("camel"),
  CAT("cat"),
  CAVE_SPIDER("cave_spider"),
  CHEST_BOAT("chest_boat"),
  CHEST_MINECART("chest_minecart"),
  CHICKEN("chicken"),
  COD("cod"),
  COMMAND_BLOCK_MINECART("command_block_minecart"),
  COW("cow"),
  CREEPER("creeper"),
  DOLPHIN("dolphin"),
  DONKEY("donkey"),
  DRAGON_FIREBALL("dragon_fireball"),
  DROWNED("drowned"),
  EGG("egg"),
  ELDER_GUARDIAN("elder_guardian"),
  END_CRYSTAL("end_crystal"),
  ENDER_DRAGON("ender_dragon"),
  ENDER_PEARL("ender_pearl"),
  ENDERMAN("enderman"),
  ENDERMITE("endermite"),
  EVOKER("evoker"),
  EVOKER_FANGS("evoker_fangs"),
  EXPERIENCE_BOTTLE("experience_bottle"),
  EXPERIENCE_ORB("experience_orb"),
  EYE_OF_ENDER("eye_of_ender"),
  FALLING_BLOCK("falling_block"),
  FIREWORK_ROCKET("firework_rocket"),
  FOX("fox"),
  FROG("frog"),
  FURNACE_MINECART("furnace_minecart"),
  GHAST("ghast"),
  GIANT("giant"),
  GLOW_ITEM_FRAME("glow_item_frame"),
  GLOW_SQUID("glow_squid"),
  GOAT("goat"),
  GUARDIAN("guardian"),
  HOGLIN("hoglin"),
  HOPPER_MINECART("hopper_minecart"),
  HORSE("horse"),
  HUSK("husk"),
  ILLUSIONER("illusioner"),
  INTERACTION("interaction"),
  IRON_GOLEM("iron_golem"),
  ITEM("item"),
  ITEM_DISPLAY("item_display"),
  ITEM_FRAME("item_frame"),
  OMINOUS_ITEM_SPAWNER("ominous_item_spawner"),
  FIREBALL("fireball"),
  LEASH_KNOT("leash_knot"),
  LIGHTNING_BOLT("lightning_bolt"),
  LLAMA("llama"),
  LLAMA_SPIT("llama_spit"),
  MAGMA_CUBE("magma_cube"),
  MARKER("marker"),
  MINECART("minecart"),
  MOOSHROOM("mooshroom"),
  MULE("mule"),
  OCELOT("ocelot"),
  PAINTING("painting"),
  PANDA("panda"),
  PARROT("parrot"),
  PHANTOM("phantom"),
  PIG("pig"),
  PIGLIN("piglin"),
  PIGLIN_BRUTE("piglin_brute"),
  PILLAGER("pillager"),
  POLAR_BEAR("polar_bear"),
  POTION("potion"),
  PUFFERFISH("pufferfish"),
  RABBIT("rabbit"),
  RAVAGER("ravager"),
  SALMON("salmon"),
  SHEEP("sheep"),
  SHULKER("shulker"),
  SHULKER_BULLET("shulker_bullet"),
  SILVERFISH("silverfish"),
  SKELETON("skeleton"),
  SKELETON_HORSE("skeleton_horse"),
  SLIME("slime"),
  SMALL_FIREBALL("small_fireball"),
  SNIFFER("sniffer"),
  SNOW_GOLEM("snow_golem"),
  SNOWBALL("snowball"),
  SPAWNER_MINECART("spawner_minecart"),
  SPECTRAL_ARROW("spectral_arrow"),
  SPIDER("spider"),
  SQUID("squid"),
  STRAY("stray"),
  STRIDER("strider"),
  TADPOLE("tadpole"),
  TEXT_DISPLAY("text_display"),
  TNT("tnt"),
  TNT_MINECART("tnt_minecart"),
  TRADER_LLAMA("trader_llama"),
  TRIDENT("trident"),
  TROPICAL_FISH("tropical_fish"),
  TURTLE("turtle"),
  VEX("vex"),
  VILLAGER("villager"),
  VINDICATOR("vindicator"),
  WANDERING_TRADER("wandering_trader"),
  WARDEN("warden"),
  WIND_CHARGE("wind_charge"),
  WITCH("witch"),
  WITHER("wither"),
  WITHER_SKELETON("wither_skeleton"),
  WITHER_SKULL("wither_skull"),
  WOLF("wolf"),
  ZOGLIN("zoglin"),
  ZOMBIE("zombie"),
  ZOMBIE_HORSE("zombie_horse"),
  ZOMBIE_VILLAGER("zombie_villager"),
  ZOMBIFIED_PIGLIN("zombified_piglin"),
  PLAYER("player"),
  FISHING_BOBBER("fishing_bobber"),
  // END
  ;

  private static final Map<String, EntityType> ENTITY_TYPES;

  static {
    final var entityTypes = values();
    ENTITY_TYPES = new HashMap<>(entityTypes.length);
    for (final var entityType : entityTypes) {
      ENTITY_TYPES.put(entityType.key, entityType);
    }
  }

  private final String key;

  EntityType(final String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }

  public static EntityType get(@NotNull String key) {
    return ENTITY_TYPES.get(key);
  }
}
