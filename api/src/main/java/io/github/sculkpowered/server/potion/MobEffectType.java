package io.github.sculkpowered.server.potion;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of all supported potion/mob effects.
 */
@SuppressWarnings("unused")
public enum MobEffectType implements Registry.Entry {

  // START
  SPEED("speed"),
  SLOWNESS("slowness"),
  HASTE("haste"),
  MINING_FATIGUE("mining_fatigue"),
  STRENGTH("strength"),
  INSTANT_HEALTH("instant_health"),
  INSTANT_DAMAGE("instant_damage"),
  JUMP_BOOST("jump_boost"),
  NAUSEA("nausea"),
  REGENERATION("regeneration"),
  RESISTANCE("resistance"),
  FIRE_RESISTANCE("fire_resistance"),
  WATER_BREATHING("water_breathing"),
  INVISIBILITY("invisibility"),
  BLINDNESS("blindness"),
  NIGHT_VISION("night_vision"),
  HUNGER("hunger"),
  WEAKNESS("weakness"),
  POISON("poison"),
  WITHER("wither"),
  HEALTH_BOOST("health_boost"),
  ABSORPTION("absorption"),
  SATURATION("saturation"),
  GLOWING("glowing"),
  LEVITATION("levitation"),
  LUCK("luck"),
  UNLUCK("unluck"),
  SLOW_FALLING("slow_falling"),
  CONDUIT_POWER("conduit_power"),
  DOLPHINS_GRACE("dolphins_grace"),
  BAD_OMEN("bad_omen"),
  HERO_OF_THE_VILLAGE("hero_of_the_village"),
  DARKNESS("darkness"),
  TRIAL_OMEN("trial_omen"),
  RAID_OMEN("raid_omen"),
  WIND_CHARGED("wind_charged"),
  WEAVING("weaving"),
  OOZING("oozing"),
  INFESTED("infested"),
  // END
  ;

  private final Key key;

  MobEffectType(final @Subst("value") String key) {
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
