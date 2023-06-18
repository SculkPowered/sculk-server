package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an inventory of a player.
 */
public interface Inventory extends Container {

    @NotNull ItemStack getItemInMainHand();

    void setItemInMainHand(@NotNull ItemStack item);

    @NotNull ItemStack getItemInOffHand();

    void setItemInOffHand(@NotNull ItemStack item);

    void setHelmet(@NotNull ItemStack helmet);

    @NotNull ItemStack getHelmet();

    void setChestplate(@NotNull ItemStack chestplate);

    @NotNull ItemStack getChestplate();

    void setLeggings(@NotNull ItemStack leggings);

    @NotNull ItemStack getLeggings();

    void setBoots(@NotNull ItemStack boots);

    @NotNull ItemStack getBoots();
}
