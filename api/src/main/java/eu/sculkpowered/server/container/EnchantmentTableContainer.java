package eu.sculkpowered.server.container;

import eu.sculkpowered.server.enchantment.Enchantment;
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
  void requirementForTopEnchantment(int requirement);

  /**
   * Gets the requirement for the top enchantment.
   *
   * @return the requirement for the top enchantment
   * @since 1.0.0
   */
  int requirementForTopEnchantment();

  /**
   * Sets the requirement for the middle enchantment.
   *
   * @param requirement the requirement for the middle enchantment
   * @since 1.0.0
   */
  void requirementForMiddleEnchantment(int requirement);

  /**
   * Gets the requirement for the middle enchantment.
   *
   * @return the requirement for the middle enchantment
   * @since 1.0.0
   */
  int requirementForMiddleEnchantment();

  /**
   * Sets the requirement for the bottom enchantment.
   *
   * @param requirement the requirement for the bottom enchantment
   * @since 1.0.0
   */
  void requirementForBottomEnchantment(int requirement);

  /**
   * Gets the requirement for the bottom enchantment.
   *
   * @return the requirement for the bottom enchantment
   * @since 1.0.0
   */
  int requirementForBottomEnchantment();

  /**
   * Sets the enchantment seed that is used to draw the enchantment names.
   *
   * @param seed the seed to draw
   * @since 1.0.0
   */
  void enchantmentSeed(int seed);

  /**
   * Gets the enchantment seed.
   *
   * @return the enchantment seed
   * @since 1.0.0
   */
  int enchantmentSeed();

  /**
   * Sets the top enchantment.
   *
   * @param enchantment the enchantment at the top
   * @since 1.0.0
   */
  void topEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the top enchantment.
   *
   * @return the top enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment topEnchantment();

  /**
   * Sets the middle enchantment.
   *
   * @param enchantment the enchantment at the middle
   * @since 1.0.0
   */
  void middleEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the middle enchantment.
   *
   * @return the middle enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment middleEnchantment();

  /**
   * Sets the bottom enchantment.
   *
   * @param enchantment the enchantment at the bottom
   * @since 1.0.0
   */
  void bottomEnchantment(@NotNull Enchantment enchantment);

  /**
   * Gets the bottom enchantment.
   *
   * @return the bottom enchantment
   * @since 1.0.0
   */
  @NotNull Enchantment bottomEnchantment();

  /**
   * Sets the level requirement for the top enchantment.
   *
   * @param level the level requirement for the top enchantment
   * @since 1.0.0
   */
  void topEnchantingLevel(int level);

  /**
   * Gets the level requirement for the top enchantment
   *
   * @return the level requirement for the top enchantment
   * @since 1.0.0
   */
  int topEnchantingLevel();

  /**
   * Sets the level requirement for the middle enchantment.
   *
   * @param level the level requirement for the middle enchantment
   * @since 1.0.0
   */
  void middleEnchantingLevel(int level);

  /**
   * Gets the level requirement for the middle enchantment
   *
   * @return the level requirement for the middle enchantment
   * @since 1.0.0
   */
  int middleEnchantingLevel();

  /**
   * Sets the level requirement for the bottom enchantment.
   *
   * @param level the level requirement for the bottom enchantment
   * @since 1.0.0
   */
  void bottomEnchantingLevel(int level);

  /**
   * Gets the level requirement for the bottom enchantment
   *
   * @return the level requirement for the bottom enchantment
   * @since 1.0.0
   */
  int bottomEnchantingLevel();
}
