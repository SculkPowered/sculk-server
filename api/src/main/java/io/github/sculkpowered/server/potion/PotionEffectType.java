package io.github.sculkpowered.server.potion;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of all supported potion effects.
 */
@SuppressWarnings("unused")
public enum PotionEffectType implements Registry.Entry {

  // START
  WATER("water"),
  MUNDANE("mundane"),
  THICK("thick"),
  AWKWARD("awkward"),
  NIGHT_VISION("night_vision"),
  LONG_NIGHT_VISION("long_night_vision"),
  INVISIBILITY("invisibility"),
  LONG_INVISIBILITY("long_invisibility"),
  LEAPING("leaping"),
  LONG_LEAPING("long_leaping"),
  STRONG_LEAPING("strong_leaping"),
  FIRE_RESISTANCE("fire_resistance"),
  LONG_FIRE_RESISTANCE("long_fire_resistance"),
  SWIFTNESS("swiftness"),
  LONG_SWIFTNESS("long_swiftness"),
  STRONG_SWIFTNESS("strong_swiftness"),
  SLOWNESS("slowness"),
  LONG_SLOWNESS("long_slowness"),
  STRONG_SLOWNESS("strong_slowness"),
  TURTLE_MASTER("turtle_master"),
  LONG_TURTLE_MASTER("long_turtle_master"),
  STRONG_TURTLE_MASTER("strong_turtle_master"),
  WATER_BREATHING("water_breathing"),
  LONG_WATER_BREATHING("long_water_breathing"),
  HEALING("healing"),
  STRONG_HEALING("strong_healing"),
  HARMING("harming"),
  STRONG_HARMING("strong_harming"),
  POISON("poison"),
  LONG_POISON("long_poison"),
  STRONG_POISON("strong_poison"),
  REGENERATION("regeneration"),
  LONG_REGENERATION("long_regeneration"),
  STRONG_REGENERATION("strong_regeneration"),
  STRENGTH("strength"),
  LONG_STRENGTH("long_strength"),
  STRONG_STRENGTH("strong_strength"),
  WEAKNESS("weakness"),
  LONG_WEAKNESS("long_weakness"),
  LUCK("luck"),
  SLOW_FALLING("slow_falling"),
  LONG_SLOW_FALLING("long_slow_falling"),
  WIND_CHARGED("wind_charged"),
  WEAVING("weaving"),
  OOZING("oozing"),
  INFESTED("infested"),
  // END
  ;

  private final Key key;

  PotionEffectType(final String key) {
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
