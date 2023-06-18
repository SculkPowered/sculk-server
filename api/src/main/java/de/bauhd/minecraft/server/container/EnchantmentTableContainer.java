package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a container of an enchantment table.
 */
public interface EnchantmentTableContainer extends Container {

    /**
     * Sets the requirement for the top enchantment.
     * @param requirement the requirement for the top enchantment
     */
    void setRequirementForTopEnchantment(int requirement);

    /**
     * Gets the requirement for the top enchantment.
     * @return the requirement for the top enchantment
     */
    int getRequirementForTopEnchantment();

    /**
     * Sets the requirement for the middle enchantment.
     * @param requirement the requirement for the middle enchantment
     */
    void setRequirementForMiddleEnchantment(int requirement);

    /**
     * Gets the requirement for the middle enchantment.
     * @return the requirement for the middle enchantment
     */
    int getRequirementForMiddleEnchantment();

    /**
     * Sets the requirement for the bottom enchantment.
     * @param requirement the requirement for the bottom enchantment
     */
    void setRequirementForBottomEnchantment(int requirement);

    /**
     * Gets the requirement for the bottom enchantment.
     * @return the requirement for the bottom enchantment
     */
    int getRequirementForBottomEnchantment();

    /**
     * Sets the enchantment seed that is used to draw the enchantment names.
     * @param seed the seed to draw
     */
    void setEnchantmentSeed(int seed);

    /**
     * Gets the enchantment seed.
     * @return the enchantment seed
     */
    int getEnchantmentSeed();

    /**
     * Sets the top enchantment.
     * @param enchantment the enchantment at the top
     */
    void setTopEnchantment(@NotNull Enchantment enchantment);

    /**
     * Gets the top enchantment.
     * @return the top enchantment
     */
    @NotNull Enchantment getTopEnchantment();

    /**
     * Sets the middle enchantment.
     * @param enchantment the enchantment at the middle
     */
    void setMiddleEnchantment(@NotNull Enchantment  enchantment);

    /**
     * Gets the middle enchantment.
     * @return the middle enchantment
     */
    @NotNull Enchantment getMiddleEnchantment();

    /**
     * Sets the bottom enchantment.
     * @param enchantment the enchantment at the bottom
     */
    void setBottomEnchantment(@NotNull Enchantment  enchantment);

    /**
     * Gets the bottom enchantment.
     * @return the bottom enchantment
     */
    @NotNull Enchantment getBottomEnchantment();

    /**
     * Sets the level requirement for the top enchantment.
     * @param level the level requirement for the top enchantment
     */
    void setTopEnchantingLevel(int level);

    /**
     * Gets the level requirement for the top enchantment
     * @return the level requirement for the top enchantment
     */
    int getTopEnchantingLevel();

    /**
     * Sets the level requirement for the middle enchantment.
     * @param level the level requirement for the middle enchantment
     */
    void setMiddleEnchantingLevel(int level);

    /**
     * Gets the level requirement for the middle enchantment
     * @return the level requirement for the middle enchantment
     */
    int getMiddleEnchantingLevel();

    /**
     * Sets the level requirement for the bottom enchantment.
     * @param level the level requirement for the bottom enchantment
     */
    void setBottomEnchantingLevel(int level);

    /**
     * Gets the level requirement for the bottom enchantment
     * @return the level requirement for the bottom enchantment
     */
    int getBottomEnchantingLevel();
}
