package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.attribute.Attribute;
import eu.sculkpowered.server.attribute.AttributeValue;
import eu.sculkpowered.server.container.equipment.EntityEquipment;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a living entity.
 */
public interface LivingEntity extends Entity {

  /**
   * Gets the health of the entity.
   *
   * @return the health of this entity
   * @since 1.0.0
   */
  float health();

  /**
   * Sets the health of the entity.
   *
   * @param health the health to set
   * @since 1.0.0
   */
  void health(float health);

  /**
   * Gets the potion effect color of the entity.
   *
   * @return the potion effect color of the entity
   * @since 1.0.0
   */
  int potionEffectColor();

  /**
   * Sets the potion effect color of the entity.
   *
   * @param effectColor the effect color to set
   * @since 1.0.0
   */
  void potionEffectColor(int effectColor);

  /**
   * Gets whether the player has a potion effect ambient.
   *
   * @return whether the player has a potion effect ambient
   * @since 1.0.0
   */
  boolean isPotionEffectAmbient();

  /**
   * Sets whether the player has a potion effect ambient.
   *
   * @param ambient whether the player has a potion effect ambient
   * @since 1.0.0
   */
  void potionEffectAmbient(boolean ambient);

  /**
   * Gets the number of arrows in the entity.
   *
   * @return the number of arrows in the entity
   * @since 1.0.0
   */
  int numberOfArrows();

  /**
   * Sets the number of arrows in the entity.
   *
   * @param arrows the number of arrows
   * @since 1.0.0
   */
  void numberOfArrows(int arrows);

  /**
   * Gets the number of bee stingers in the entity.
   *
   * @return the number of bee stingers in the entity
   * @since 1.0.0
   */
  int numberOfBeeStingers();

  /**
   * Sets the number of bee stingers in the entity.
   *
   * @param beeStingers the number of bee stingers
   * @since 1.0.0
   */
  void numberOfBeeStingers(int beeStingers);

  /**
   * Gets the attribute value by the attribute.
   *
   * @param attribute the attribute
   * @return the value
   * @since 1.0.0
   */
  @NotNull AttributeValue attribute(@NotNull Attribute attribute);

  /**
   * Gets the equipment from the entity.
   * @return the equipment
   */
  @NotNull EntityEquipment equipment();
}
