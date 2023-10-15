package de.bauhd.sculk.container;

import de.bauhd.sculk.potion.PotionEffect;
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
  void setPowerLevel(@Range(from = 0, to = 4) int powerLevel);

  /**
   * Gets the power level of the beacon.
   *
   * @return the power level of this beacon
   * @since 1.0.0
   */
  int getPowerLevel();

  /**
   * Sets the first potion effect of the beacon.
   *
   * @param potionEffect the first potion effect
   * @since 1.0.0
   */
  void setFirstPotionEffect(@NotNull PotionEffect potionEffect);

  /**
   * Gets the first potion effect of the beacon.
   *
   * @return the first potion effect
   * @since 1.0.0
   */
  @NotNull PotionEffect getFirstPotionEffect();

  /**
   * Sets the second potion effect of the beacon.
   *
   * @param potionEffect the second potion effect
   * @since 1.0.0
   */
  void setSecondPotionEffect(@NotNull PotionEffect potionEffect);

  /**
   * Gets the second potion effect of the beacon.
   *
   * @return the second potion effect
   * @since 1.0.0
   */
  @NotNull PotionEffect getSecondPotionEffect();
}
