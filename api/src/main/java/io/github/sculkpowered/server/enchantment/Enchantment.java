package io.github.sculkpowered.server.enchantment;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of all enchantments supported.
 */
@SuppressWarnings("unused")
public enum Enchantment implements Registry.Entry {

  // START
  PROTECTION("protection"),
  FIRE_PROTECTION("fire_protection"),
  FEATHER_FALLING("feather_falling"),
  BLAST_PROTECTION("blast_protection"),
  PROJECTILE_PROTECTION("projectile_protection"),
  RESPIRATION("respiration"),
  AQUA_AFFINITY("aqua_affinity"),
  THORNS("thorns"),
  DEPTH_STRIDER("depth_strider"),
  FROST_WALKER("frost_walker"),
  BINDING_CURSE("binding_curse"),
  SOUL_SPEED("soul_speed"),
  SWIFT_SNEAK("swift_sneak"),
  SHARPNESS("sharpness"),
  SMITE("smite"),
  BANE_OF_ARTHROPODS("bane_of_arthropods"),
  KNOCKBACK("knockback"),
  FIRE_ASPECT("fire_aspect"),
  LOOTING("looting"),
  SWEEPING_EDGE("sweeping_edge"),
  EFFICIENCY("efficiency"),
  SILK_TOUCH("silk_touch"),
  UNBREAKING("unbreaking"),
  FORTUNE("fortune"),
  POWER("power"),
  PUNCH("punch"),
  FLAME("flame"),
  INFINITY("infinity"),
  LUCK_OF_THE_SEA("luck_of_the_sea"),
  LURE("lure"),
  LOYALTY("loyalty"),
  IMPALING("impaling"),
  RIPTIDE("riptide"),
  CHANNELING("channeling"),
  MULTISHOT("multishot"),
  QUICK_CHARGE("quick_charge"),
  PIERCING("piercing"),
  DENSITY("density"),
  BREACH("breach"),
  WIND_BURST("wind_burst"),
  MENDING("mending"),
  VANISHING_CURSE("vanishing_curse"),
  // END
  ;

  private final Key key;

  Enchantment(final String key) {
    this.key = Key.key(Key.MINECRAFT_NAMESPACE, key);
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  @Override
  public int id() {
    return this.ordinal();
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }
}
