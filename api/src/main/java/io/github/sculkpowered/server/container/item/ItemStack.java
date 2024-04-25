package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.container.item.data.DataComponent;
import java.util.Map;
import org.jetbrains.annotations.ApiStatus;
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
  private final ItemMeta meta;

  private ItemStack(
      final @NotNull Material material,
      final int amount,
      final @NotNull ItemMeta meta
  ) {
    this.material = material;
    this.amount = amount;
    this.meta = meta;
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
    return new ItemStack(this.material, amount, this.meta);
  }

  public @NotNull ItemStack withMeta(final ItemMeta itemMeta) {
    return new ItemStack(this.material, this.amount, itemMeta);
  }

  /**
   * Gets the metadata of the item.
   *
   * @return the metadata of the item
   * @since 1.0.0
   */
  public @NotNull ItemMeta meta() {
    return this.meta;
  }

  /**
   * Returns true if this item is air.
   *
   * @return true if it is empty.
   * @since 1.0.0
   */
  public boolean isEmpty() {
    return this.material == Material.AIR;
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
    return new ItemStack(material, amount, new ItemMeta(Map.of()));
  }

  @ApiStatus.Internal
  public static @NotNull ItemStack itemStack(
      final @NotNull Material material,
      final int amount,
      final Map<DataComponent<Object>, Object> components
      ) {
    return new ItemStack(material, amount, new ItemMeta(components));
  }

  public static @NotNull ItemStack empty() {
    return EMPTY;
  }
}
