package io.github.sculkpowered.server.util;

import io.github.sculkpowered.server.container.item.ItemStack;
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
    final var previous = this.items[index];
    this.items[index] = item;
    return previous;
  }

  @Override
  public int size() {
    return this.size;
  }
}
