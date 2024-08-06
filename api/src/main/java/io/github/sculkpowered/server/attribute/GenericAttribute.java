package io.github.sculkpowered.server.attribute;

public final class GenericAttribute {

  public static final Attribute ARMOR =
      new Attribute("generic.armor", 0, 0F, 0F, 30F);
  public static final Attribute ARMOR_TOUGHNESS =
      new Attribute("generic.armor_toughness", 1, 0F, 0F, 20F);
  public static final Attribute ATTACK_DAMAGE =
      new Attribute("generic.attack_damage", 2, 2F, 0F, 2048F);
  public static final Attribute ATTACK_KNOCKBACK =
      new Attribute("generic.attack_knockback", 3, 0F, 0F, 5F);
  public static final Attribute ATTACK_SPEED =
      new Attribute("generic.attack_speed", 4, 4F, 0F, 1024F);
  public static final Attribute BURNING_TIME =
      new Attribute("generic.burning_time", 7, 0F, 0F, 1024F);
  public static final Attribute EXPLOSION_KNOCKBACK_RESISTANCE =
      new Attribute("generic.explosion_knockback_resistance", 8, 0F, 0F, 1F);
  public static final Attribute FALL_DAMAGE_MULTIPLIER =
      new Attribute("generic.fall_damage_multiplier", 10, 1F, 0F, 100F);
  public static final Attribute FLYING_SPEED =
      new Attribute("generic.flying_speed", 11, 0.4F, 0F, 1024F);
  public static final Attribute FOLLOW_RANGE =
      new Attribute("generic.follow_range", 12, 32F, 0F, 2048F);
  public static final Attribute GRAVITY =
      new Attribute("generic.gravity", 13, 0.08F, -1F, 1F);
  public static final Attribute JUMP_STRENGTH =
      new Attribute("generic.jump_strength", 14, 0.42F, 0F, 32F);
  public static final Attribute KNOCKBACK_RESISTANCE =
      new Attribute("generic.knockback_resistance", 15, 0F, 0F, 1F);
  public static final Attribute LUCK =
      new Attribute("generic.luck", 16, 0F, -1024F, 1024F);
  public static final Attribute MAX_ABSORPTION =
      new Attribute("generic.max_health", 17, 0F, 0F, 2048F);
  public static final Attribute MAX_HEALTH =
      new Attribute("generic.max_health", 18, 20F, 1F, 1024F);
  public static final Attribute MOVEMENT_EFFICIENCY =
      new Attribute("generic.movement_efficiency", 20, 0F, 0F, 1F);
  public static final Attribute MOVEMENT_SPEED =
      new Attribute("generic.movement_speed", 21, 0.7F, 0F, 1024F);
  public static final Attribute OXYGEN_BONUS =
      new Attribute("generic.oxygen_bonus", 22, 0F, 0F, 1024F);
  public static final Attribute SAFE_FALL_DISTANCE =
      new Attribute("generic.safe_fall_distance", 23, 3F, -1024F, 1024F);
  public static final Attribute SCALE =
      new Attribute("generic.scale", 24, 1F, 0.0625F, 16F);
  public static final Attribute STEP_HEIGHT =
      new Attribute("generic.step_height", 27, 0.6F, 0F, 10F);
  public static final Attribute SUBMERGED_MINING_SPEED =
      new Attribute("generic.submerged_mining_speed", 28, 0.2F, 0F, 20F);
  public static final Attribute SWEEPING_DAMAGE_RATIO =
      new Attribute("generic.sweeping_damage_ratio", 29, 0F, 0F, 1F);
  public static final Attribute WATER_MOVEMENT_EFFICIENCY =
      new Attribute("generic.water_movement_efficiency", 30, 0F, 0F, 1F);
}
