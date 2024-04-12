package io.github.sculkpowered.server.attribute;

import org.jetbrains.annotations.NotNull;

public record Attribute(@NotNull String key, float def, float max) {

  public static final Attribute GENERIC_ARMOR =
      new Attribute("generic.armor", 0F, 30F);
  public static final Attribute GENERIC_ARMOR_TOUGHNESS =
      new Attribute("generic.armor_toughness", 0F, 20F);
  public static final Attribute GENERIC_ATTACK_DAMAGE =
      new Attribute("generic.attack_damage", 2F, 2048F);
  public static final Attribute GENERIC_ATTACK_KNOCKBACK =
      new Attribute("generic.attack_knockback", 0F, 5F);
  public static final Attribute GENERIC_ATTACK_SPEED =
      new Attribute("generic.attack_speed", 4F, 1024F);
  public static final Attribute GENERIC_BLOCK_INTERACTION_RANGE =
      new Attribute("generic.block_interaction_range", 4.5F, 64F);
  public static final Attribute GENERIC_ENTITY_INTERACTION_RANGE =
      new Attribute("generic.entity_interaction_range", 3F, 64F);
  public static final Attribute GENERIC_FLYING_SPEED =
      new Attribute("generic.flying_speed", 0.4F, 1024F);
  public static final Attribute GENERIC_FOLLOW_RANGE =
      new Attribute("generic.follow_range", 32F, 2048F);
  public static final Attribute HORSE_JUMP_STRENGTH =
      new Attribute("horse.jump_strength", 0.7F, 2F);
  public static final Attribute GENERIC_KNOCKBACK_RESISTANCE =
      new Attribute("generic.knockback_resistance", 0F, 1F);
  public static final Attribute GENERIC_LUCK =
      new Attribute("generic.luck", 0F, 1024F);
  public static final Attribute GENERIC_MAX_ABSORPTION =
      new Attribute("generic.max_health", 0F, 2048F);
  public static final Attribute GENERIC_MAX_HEALTH =
      new Attribute("generic.max_health", 20F, 1024F);
  public static final Attribute GENERIC_MOVEMENT_SPEED =
      new Attribute("generic.movement_speed", 0.7F, 1024F);
  public static final Attribute GENERIC_SCALE =
      new Attribute("generic.scale", 1F, 16F);
  public static final Attribute ZOMBIE_SPAWN_REINFORCEMENTS =
      new Attribute("zombie.spawn_reinforcements", 0F, 1F);
  public static final Attribute GENERIC_STEP_HEIGHT =
      new Attribute("zombie.spawn_reinforcements", 0.6F, 10F);

  @Override
  public String toString() {
    return "Attribute{" +
        "key='" + this.key + '\'' +
        ", def=" + this.def +
        ", max=" + this.max +
        '}';
  }
}
