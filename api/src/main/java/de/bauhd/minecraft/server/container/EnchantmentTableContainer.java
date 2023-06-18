package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a container of an enchantment table.
 */
public interface EnchantmentTableContainer extends Container {

    void setRequirementForTopEnchantment(int requirement);

    int getRequirementForTopEnchantment();

    void setRequirementForMiddleEnchantment(int requirement);

    int getRequirementForMiddleEnchantment();

    void setRequirementForBottomEnchantment(int requirement);

    int getRequirementForBottomEnchantment();

    void setEnchantmentSeed(int seed);

    int getEnchantmentSeed();

    void setTopEnchantment(@NotNull Enchantment enchantment);

    @NotNull Enchantment getTopEnchantment();

    void setMiddleEnchantment(@NotNull Enchantment  enchantment);

    @NotNull Enchantment getMiddleEnchantment();

    void setBottomEnchantment(@NotNull Enchantment  enchantment);

    @NotNull Enchantment getBottomEnchantment();

    void setTopEnchantingLevel(int level);

    int getTopEnchantingLevel();

    void setMiddleEnchantingLevel(int level);

    int getMiddleEnchantingLevel();

    void setBottomEnchantingLevel(int level);

    int getBottomEnchantingLevel();
}
