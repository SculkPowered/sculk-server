package de.bauhd.sculk.entity;

import de.bauhd.sculk.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class SculkItemDisplay extends AbstractDisplay implements ItemDisplay {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ITEM_DISPLAY;
  }

  @Override
  public @NotNull ItemStack getItem() {
    return this.metadata.getItem(22, ItemStack.empty());
  }

  @Override
  public void setItem(@NotNull ItemStack item) {
    this.metadata.setItem(22, item);
  }

  @Override
  public @NotNull DisplayType getDisplayType() {
    return this.metadata.getEnum(23, DisplayType.NONE);
  }

  @Override
  public void setDisplayType(@NotNull DisplayType displayType) {
    this.metadata.setByte(23, (byte) displayType.ordinal());
  }
}
