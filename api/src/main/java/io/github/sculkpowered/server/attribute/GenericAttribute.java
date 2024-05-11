package io.github.sculkpowered.server.attribute;

public final class GenericAttribute {

  public static final Attribute ARMOR =
      new Attribute("generic.armor", 0, 0F, 30F);
  public static final Attribute ARMOR_TOUGHNESS =
      new Attribute("generic.armor_toughness", 1, 0F, 20F);
  public static final Attribute ATTACK_DAMAGE =
      new Attribute("generic.attack_damage", 2, 2F, 2048F);
  public static final Attribute ATTACK_KNOCKBACK =
      new Attribute("generic.attack_knockback", 3, 0F, 5F);
  public static final Attribute ATTACK_SPEED =
      new Attribute("generic.attack_speed", 4, 4F, 1024F);
  public static final Attribute FALL_DAMAGE_MULTIPLIER =
      new Attribute("generic.fall_damage_multiplier", 8, 1F, 100F);
  public static final Attribute FLYING_SPEED =
      new Attribute("generic.flying_speed", 9, 0.4F, 1024F);
  public static final Attribute FOLLOW_RANGE =
      new Attribute("generic.follow_range", 10, 32F, 2048F);
  public static final Attribute GRAVITY =
      new Attribute("generic.gravity", 11, 0.08F, 1F);
  public static final Attribute JUMP_STRENGTH =
      new Attribute("generic.jump_strength", 12, 0.42F, 32F);
  public static final Attribute KNOCKBACK_RESISTANCE =
      new Attribute("generic.knockback_resistance", 13, 0F, 1F);
  public static final Attribute LUCK =
      new Attribute("generic.luck", 14, 0F, 1024F);
  public static final Attribute MAX_ABSORPTION =
      new Attribute("generic.max_health", 15, 0F, 2048F);
  public static final Attribute MAX_HEALTH =
      new Attribute("generic.max_health", 16, 20F, 1024F);
  public static final Attribute MOVEMENT_SPEED =
      new Attribute("generic.movement_speed", 17, 0.7F, 1024F);
  public static final Attribute SAFE_FALL_DISTANCE =
      new Attribute("generic.safe_fall_distance", 18, 3F, 1024F);
  public static final Attribute SCALE =
      new Attribute("generic.scale", 19, 1, 16F);
  public static final Attribute STEP_HEIGHT =
      new Attribute("generic.step_height", 21, 0.6F, 10F);
}
