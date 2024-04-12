package io.github.sculkpowered.server.attribute;

import org.jetbrains.annotations.NotNull;

public record Attribute(@NotNull String key, int id, float def, float max) {

  public static final Attribute GENERIC_ARMOR =
      new Attribute("generic.armor", 0, 0F, 30F);
  public static final Attribute GENERIC_ARMOR_TOUGHNESS =
      new Attribute("generic.armor_toughness", 1, 0F, 20F);
  public static final Attribute GENERIC_ATTACK_DAMAGE =
      new Attribute("generic.attack_damage", 2, 2F, 2048F);
  public static final Attribute GENERIC_ATTACK_KNOCKBACK =
      new Attribute("generic.attack_knockback", 3, 0F, 5F);
  public static final Attribute GENERIC_ATTACK_SPEED =
      new Attribute("generic.attack_speed", 4, 4F, 1024F);
  public static final Attribute GENERIC_BLOCK_INTERACTION_RANGE =
      new Attribute("generic.block_interaction_range", 5, 4.5F, 64F);
  public static final Attribute GENERIC_ENTITY_INTERACTION_RANGE =
      new Attribute("generic.entity_interaction_range", 6, 3F, 64F);
  public static final Attribute GENERIC_FLYING_SPEED =
      new Attribute("generic.flying_speed", 7, 0.4F, 1024F);
  public static final Attribute GENERIC_FOLLOW_RANGE =
      new Attribute("generic.follow_range", 8, 32F, 2048F);
  public static final Attribute HORSE_JUMP_STRENGTH =
      new Attribute("horse.jump_strength", 9, 0.7F, 2F);
  public static final Attribute GENERIC_KNOCKBACK_RESISTANCE =
      new Attribute("generic.knockback_resistance", 10, 0F, 1F);
  public static final Attribute GENERIC_LUCK =
      new Attribute("generic.luck", 11, 0F, 1024F);
  public static final Attribute GENERIC_MAX_ABSORPTION =
      new Attribute("generic.max_health", 12, 0F, 2048F);
  public static final Attribute GENERIC_MAX_HEALTH =
      new Attribute("generic.max_health", 13, 20F, 1024F);
  public static final Attribute GENERIC_MOVEMENT_SPEED =
      new Attribute("generic.movement_speed", 14, 0.7F, 1024F);
  public static final Attribute GENERIC_SCALE =
      new Attribute("generic.scale", 15, 1F, 16F);
  public static final Attribute ZOMBIE_SPAWN_REINFORCEMENTS =
      new Attribute("zombie.spawn_reinforcements", 16, 0F, 1F);
  public static final Attribute GENERIC_STEP_HEIGHT =
      new Attribute("zombie.spawn_reinforcements", 17, 0.6F, 10F);

  @Override
  public String toString() {
    return "Attribute{" +
        "key='" + this.key + '\'' +
        ", id=" + this.id +
        ", def=" + this.def +
        ", max=" + this.max +
        '}';
  }
}
