package eu.sculkpowered.server.container.item;

import eu.sculkpowered.server.container.item.data.DataComponentType;
import eu.sculkpowered.server.container.item.data.DataComponents;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item.
 */
public final class ItemStack {

  /**
   * The default item.
   */
  private static final ItemStack EMPTY = ItemStack.itemStack(ItemType.AIR, 0);

  private final ItemType type;
  private final int amount;
  private final DataComponents components;

  private ItemStack(
      final @NotNull ItemType type,
      final int amount,
      final @NotNull DataComponents components
  ) {
    this.type = type;
    this.amount = amount;
    this.components = components;
  }

  /**
   * Gets the type of the item.
   *
   * @return the type of item
   * @since 1.0.0
   */
  public ItemType type() {
    return this.type;
  }

  /**
   * Gets the amount of items.
   *
   * @return the amount of items
   * @since 1.0.0
   */
  public int amount() {
    return this.amount;
  }

  /**
   * Sets the amount of items.
   *
   * @return the same item
   * @since 1.0.0
   */
  public @NotNull ItemStack amount(final int amount) {
    return new ItemStack(this.type, amount, this.components);
  }

  public @NotNull ItemStack withComponents(final DataComponents components) {
    return new ItemStack(this.type, this.amount, components);
  }

  /**
   * Gets the data components of the item.
   *
   * @return the data components of the item
   * @since 1.0.0
   */
  public @NotNull DataComponents components() {
    return this.components;
  }

  /**
   * Returns true if this item is air.
   *
   * @return true if it is empty.
   * @since 1.0.0
   */
  public boolean isEmpty() {
    return this.type == ItemType.AIR || this.amount == 0;
  }

  public <T> T get(final @NotNull DataComponentType<T> type) {
    final var value = this.components.get(type);
    if (value == null) {
      return Objects.requireNonNull(this.type.components().get(type)).get();
    } else if (value.isPresent()) {
      return value.get();
    }
    return null;
  }

  @Override
  public String toString() {
    return "ItemStack{" +
        "type=" + this.type +
        ", amount=" + this.amount +
        '}';
  }

  public static @NotNull ItemStack itemStack(final @NotNull ItemType type) {
    return itemStack(type, 1);
  }

  public static @NotNull ItemStack itemStack(final @NotNull ItemType type, final int amount) {
    return itemStack(type, amount, DataComponents.empty());
  }

  public static @NotNull ItemStack itemStack(
      final @NotNull ItemType type,
      final @NotNull DataComponents components
  ) {
    return itemStack(type, 1, components);
  }

  public static @NotNull ItemStack itemStack(
      final @NotNull ItemType type,
      final int amount,
      final @NotNull DataComponents components
      ) {
    return new ItemStack(type, amount, components);
  }

  public static @NotNull ItemStack empty() {
    return EMPTY;
  }
}
