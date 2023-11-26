package io.github.sculkpowered.server.container.item;

import java.util.function.Consumer;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item.
 */
public class ItemStack {

  /**
   * The default item.
   */
  private static final ItemStack EMPTY = ItemStack.itemStack(Material.AIR, 0);

  private final Material material;
  private final int amount;
  private final CompoundBinaryTag nbt;

  private ItemStack(final @NotNull Material material) {
    this(material, 1);
  }

  private ItemStack(final @NotNull Material material, final int amount) {
    this(material, amount, CompoundBinaryTag.empty());
  }

  private ItemStack(final @NotNull Material material, final int amount,
      final @NotNull CompoundBinaryTag nbt) {
    this.material = material;
    this.amount = amount;
    this.nbt = nbt;
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
    return new ItemStack(this.material, amount, this.nbt);
  }

  public @NotNull ItemStack withMeta(final Consumer<ItemMeta> consumer) {
    final var itemMeta = new ItemMeta(this.nbt);
    consumer.accept(itemMeta);
    return new ItemStack(this.material, this.amount, itemMeta.build());
  }

  /**
   * Gets the nbt of item.
   *
   * @return the nbt of item
   * @since 1.0.0
   */
  public @NotNull CompoundBinaryTag nbt() {
    return this.nbt;
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
    return new ItemStack(material);
  }

  public static @NotNull ItemStack itemStack(final @NotNull Material material, final int amount) {
    return new ItemStack(material, amount);
  }

  @ApiStatus.Internal
  public static @NotNull ItemStack itemStack(final @NotNull Material material, final int amount,
      final CompoundBinaryTag nbt) {
    return new ItemStack(material, amount, nbt);
  }

  public static @NotNull ItemStack empty() {
    return EMPTY;
  }
}
