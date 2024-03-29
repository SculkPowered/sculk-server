package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.potion.PotionEffect;
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
   * @param potionEffect the first potion effect
   * @since 1.0.0
   */
  void firstPotionEffect(@NotNull PotionEffect potionEffect);

  /**
   * Gets the first potion effect of the beacon.
   *
   * @return the first potion effect
   * @since 1.0.0
   */
  @NotNull PotionEffect firstPotionEffect();

  /**
   * Sets the second potion effect of the beacon.
   *
   * @param potionEffect the second potion effect
   * @since 1.0.0
   */
  void secondPotionEffect(@NotNull PotionEffect potionEffect);

  /**
   * Gets the second potion effect of the beacon.
   *
   * @return the second potion effect
   * @since 1.0.0
   */
  @NotNull PotionEffect secondPotionEffect();
}
