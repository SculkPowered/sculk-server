package de.bauhd.sculk.enchantment;

/**
 * An enum of all enchantments supported.
 */
@SuppressWarnings("unused")
public enum Enchantment {

  // START
  PROTECTION("minecraft:protection"),
  FIRE_PROTECTION("minecraft:fire_protection"),
  FEATHER_FALLING("minecraft:feather_falling"),
  BLAST_PROTECTION("minecraft:blast_protection"),
  PROJECTILE_PROTECTION("minecraft:projectile_protection"),
  RESPIRATION("minecraft:respiration"),
  AQUA_AFFINITY("minecraft:aqua_affinity"),
  THORNS("minecraft:thorns"),
  DEPTH_STRIDER("minecraft:depth_strider"),
  FROST_WALKER("minecraft:frost_walker"),
  BINDING_CURSE("minecraft:binding_curse"),
  SOUL_SPEED("minecraft:soul_speed"),
  SWIFT_SNEAK("minecraft:swift_sneak"),
  SHARPNESS("minecraft:sharpness"),
  SMITE("minecraft:smite"),
  BANE_OF_ARTHROPODS("minecraft:bane_of_arthropods"),
  KNOCKBACK("minecraft:knockback"),
  FIRE_ASPECT("minecraft:fire_aspect"),
  LOOTING("minecraft:looting"),
  SWEEPING("minecraft:sweeping"),
  EFFICIENCY("minecraft:efficiency"),
  SILK_TOUCH("minecraft:silk_touch"),
  UNBREAKING("minecraft:unbreaking"),
  FORTUNE("minecraft:fortune"),
  POWER("minecraft:power"),
  PUNCH("minecraft:punch"),
  FLAME("minecraft:flame"),
  INFINITY("minecraft:infinity"),
  LUCK_OF_THE_SEA("minecraft:luck_of_the_sea"),
  LURE("minecraft:lure"),
  LOYALTY("minecraft:loyalty"),
  IMPALING("minecraft:impaling"),
  RIPTIDE("minecraft:riptide"),
  CHANNELING("minecraft:channeling"),
  MULTISHOT("minecraft:multishot"),
  QUICK_CHARGE("minecraft:quick_charge"),
  PIERCING("minecraft:piercing"),
  MENDING("minecraft:mending"),
  VANISHING_CURSE("minecraft:vanishing_curse"),
  // END
  ;

  private final String key;

  Enchantment(final String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }
}
