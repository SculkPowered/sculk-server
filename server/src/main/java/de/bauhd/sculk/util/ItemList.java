package de.bauhd.sculk.util;

import de.bauhd.sculk.container.item.ItemStack;

import java.util.AbstractList;

public final class ItemList extends AbstractList<ItemStack> {

    private final ItemStack[] items;
    private final int size;

    public ItemList(int size) {
        this.items = new ItemStack[size];
        for (var i = 0; i < size; i++) {
            this.items[i] = ItemStack.empty();
        }
        this.size = size;
    }

    @Override
    public ItemStack get(int index) {
        return this.items[index];
    }

    @Override
    public ItemStack set(int index, ItemStack item) {
        this.items[index] = item;
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }
}
