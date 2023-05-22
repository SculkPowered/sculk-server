package de.bauhd.minecraft.server.inventory;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public abstract class MineInventory implements Inventory {

    public final Int2ObjectMap<ItemStack> itemStacks = new Int2ObjectOpenHashMap<>(0);

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.itemStacks.put(index, itemStack);
    }

    @Override
    public ItemStack getItem(int index) {
        return this.itemStacks.get(index);
    }
}
