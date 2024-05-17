package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.container.item.data.DataComponentType;
import io.github.sculkpowered.server.container.item.data.DataComponents;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item.
 */
public final class ItemStack {

  /**
   * The default item.
   */
  private static final ItemStack EMPTY = ItemStack.itemStack(Material.AIR, 0);

  private final Material material;
  private final int amount;
  private final DataComponents components;

  private ItemStack(
      final @NotNull Material material,
      final int amount,
      final @NotNull DataComponents components
  ) {
    this.material = material;
    this.amount = amount;
    this.components = components;
  }

  /**
   * Gets the material of an item.
   *
   * @return the material of item
   * @since 1.0.0
   */
  public Material material() {
    return this.material;
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
    return new ItemStack(this.material, amount, this.components);
  }

  public @NotNull ItemStack withComponents(final DataComponents components) {
    return new ItemStack(this.material, this.amount, components);
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
    return this.material == Material.AIR || this.amount == 0;
  }

  public <T> T get(final @NotNull DataComponentType<T> type) {
    final var value = this.components.get(type);
    if (value == null) {
      return Objects.requireNonNull(this.material.components().get(type)).get();
    } else if (value.isPresent()) {
      return value.get();
    }
    return null;
  }

  @Override
  public String toString() {
    return "ItemStack{" +
        "material=" + this.material +
        ", amount=" + this.amount +
        '}';
  }

  public static @NotNull ItemStack itemStack(final @NotNull Material material) {
    return itemStack(material, 1);
  }

  public static @NotNull ItemStack itemStack(final @NotNull Material material, final int amount) {
    return itemStack(material, amount, DataComponents.empty());
  }

  public static @NotNull ItemStack itemStack(
      final @NotNull Material material,
      final @NotNull DataComponents components
  ) {
    return itemStack(material, 1, components);
  }

  public static @NotNull ItemStack itemStack(
      final @NotNull Material material,
      final int amount,
      final @NotNull DataComponents components
      ) {
    return new ItemStack(material, amount, components);
  }

  public static @NotNull ItemStack empty() {
    return EMPTY;
  }
}
