package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.util.ItemList;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractContainer implements Container {

  private final Container.Type type;
  private final ItemList items;
  private int state;

  public AbstractContainer(final Container.Type type) {
    this.type = type;
    this.items = new ItemList(type.size());
  }

  @Override
  public @NotNull Type type() {
    return this.type;
  }

  @Override
  public void item(int index, @NotNull ItemStack itemStack) {
    this.items.set(index, itemStack);
  }

  @Override
  public @NotNull ItemStack item(int index) {
    return this.items.get(index);
  }

  @Override
  public @NotNull ItemList items() {
    return this.items;
  }

  protected int incrementState() {
    return ++this.state;
  }

  public int state() {
    return this.state;
  }
}
