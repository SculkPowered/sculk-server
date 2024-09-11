package eu.sculkpowered.server.container;

import eu.sculkpowered.server.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Represents a container of a beacon block.
 */
public interface BeaconContainer extends Container {

  /**
   * Sets the power level of the beacon.
   *
   * @param powerLevel the power level to set
   * @since 1.0.0
   */
  void powerLevel(@Range(from = 0, to = 4) int powerLevel);

  /**
   * Gets the power level of the beacon.
   *
   * @return the power level of this beacon
   * @since 1.0.0
   */
  int powerLevel();

  /**
   * Sets the first potion effect of the beacon.
   *
   * @param potionType the first potion effect
   * @since 1.0.0
   */
  void firstPotionEffect(@NotNull PotionType potionType);

  /**
   * Gets the first potion effect of the beacon.
   *
   * @return the first potion effect
   * @since 1.0.0
   */
  @NotNull
  PotionType firstPotionEffect();

  /**
   * Sets the second potion effect of the beacon.
   *
   * @param potionType the second potion effect
   * @since 1.0.0
   */
  void secondPotionEffect(@NotNull PotionType potionType);

  /**
   * Gets the second potion effect of the beacon.
   *
   * @return the second potion effect
   * @since 1.0.0
   */
  @NotNull
  PotionType secondPotionEffect();
}
