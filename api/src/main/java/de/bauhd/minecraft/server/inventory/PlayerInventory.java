package de.bauhd.minecraft.server.inventory;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface PlayerInventory extends Inventory {

    @Nullable ItemStack getItemInMainHand();

    @Nullable ItemStack getItemInOffHand();

    void setItemInOffHand(@Nullable ItemStack item);

    void setHelmet(@Nullable ItemStack helmet);

    @Nullable ItemStack getHelmet();

    void setChestplate(@Nullable ItemStack chestplate);

    @Nullable ItemStack getChestplate();

    void setLeggings(@Nullable ItemStack leggings);

    @Nullable ItemStack getLeggings();

    void setBoots(@Nullable ItemStack boots);

    @Nullable ItemStack getBoots();
}
