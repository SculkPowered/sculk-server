package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a container of an enchantment table.
 */
public interface EnchantmentTableContainer extends Container {

  /**
   * Sets the requirement for the top enchantment.
   *
   * @param requirement the requirement for the top enchantment
   * @since 1.0.0
   */
  void setRequirementForTopEnchantment(int requirement);

  /**
   * Gets the requirement for the top enchantment.
   *
   * @return the requirement for the top enchantment
   * @since 1.0.0
   */
  int getRequirementForTopEnchantment();

  /**
   * Sets the requirement for the middle enchantment.
   *
   * @param requirement the requirement for the middle enchantment
   * @since 1.0.0
   */
  void setRequirementForMiddleEnchantment(int requirement);

  /**
   * Gets the requirement for the middle enchantment.
   *
   * @return the requirement for the middle enchantment
   * @since 1.0.0
   */
  int getRequirementForMiddleEnchantment();

  /**
   * Sets the requirement for the bottom enchantment.
   *
   * @param requirement the requirement for the bottom enchantment
   * @since 1.0.0
   */
  void setRequirementForBottomEnchantment(int requirement);

  /**
   * Gets the requirement for the bottom enchantment.
   *
   * @return the requirement for the bottom enchantment
   * @since 1.0.0
   */
  int getRequirementForBottomEnchantment();

  /**
   * Sets the enchantment seed that is used to draw the enchantment names.
   *
   * @param seed the seed to draw
   * @since 1.0.0
   */
  void setEnchantmentSeed(int seed);

  /**
   * Gets the enchantment seed.
   *
   * @return the enchantment seed
   * @since 1.0.0
   */
  int getEnchantmentSeed();

  /**
   * Sets the top enchantment.
   *
   * @param enchantment the enchantment at the top
   * @since 1.0.0
   */
  void setTopEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the top enchantment.
   *
   * @return the top enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment getTopEnchantment();

  /**
   * Sets the middle enchantment.
   *
   * @param enchantment the enchantment at the middle
   * @since 1.0.0
   */
  void setMiddleEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the middle enchantment.
   *
   * @return the middle enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment getMiddleEnchantment();

  /**
   * Sets the bottom enchantment.
   *
   * @param enchantment the enchantment at the bottom
   * @since 1.0.0
   */
  void setBottomEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the bottom enchantment.
   *
   * @return the bottom enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment getBottomEnchantment();

  /**
   * Sets the level requirement for the top enchantment.
   *
   * @param level the level requirement for the top enchantment
   * @since 1.0.0
   */
  void setTopEnchantingLevel(int level);

  /**
   * Gets the level requirement for the top enchantment
   *
   * @return the level requirement for the top enchantment
   * @since 1.0.0
   */
  int getTopEnchantingLevel();

  /**
   * Sets the level requirement for the middle enchantment.
   *
   * @param level the level requirement for the middle enchantment
   * @since 1.0.0
   */
  void setMiddleEnchantingLevel(int level);

  /**
   * Gets the level requirement for the middle enchantment
   *
   * @return the level requirement for the middle enchantment
   * @since 1.0.0
   */
  int getMiddleEnchantingLevel();

  /**
   * Sets the level requirement for the bottom enchantment.
   *
   * @param level the level requirement for the bottom enchantment
   * @since 1.0.0
   */
  void setBottomEnchantingLevel(int level);

  /**
   * Gets the level requirement for the bottom enchantment
   *
   * @return the level requirement for the bottom enchantment
   * @since 1.0.0
   */
  int getBottomEnchantingLevel();
}
