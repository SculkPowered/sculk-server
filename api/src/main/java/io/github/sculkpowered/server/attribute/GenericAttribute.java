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
  public static final Attribute BLOCK_INTERACTION_RANGE =
      new Attribute("generic.block_interaction_range", 5, 4.5F, 64F);
  public static final Attribute ENTITY_INTERACTION_RANGE =
      new Attribute("generic.entity_interaction_range", 6, 3F, 64F);
  public static final Attribute FLYING_SPEED =
      new Attribute("generic.flying_speed", 7, 0.4F, 1024F);
  public static final Attribute FOLLOW_RANGE =
      new Attribute("generic.follow_range", 8, 32F, 2048F);
  public static final Attribute KNOCKBACK_RESISTANCE =
      new Attribute("generic.knockback_resistance", 10, 0F, 1F);
  public static final Attribute LUCK =
      new Attribute("generic.luck", 11, 0F, 1024F);
  public static final Attribute MAX_ABSORPTION =
      new Attribute("generic.max_health", 12, 0F, 2048F);
  public static final Attribute MAX_HEALTH =
      new Attribute("generic.max_health", 13, 20F, 1024F);
  public static final Attribute MOVEMENT_SPEED =
      new Attribute("generic.movement_speed", 14, 0.7F, 1024F);
  public static final Attribute SCALE =
      new Attribute("generic.scale", 15, 1F, 16F);
  public static final Attribute STEP_HEIGHT =
      new Attribute("generic.step_height", 17, 0.6F, 10F);
}
