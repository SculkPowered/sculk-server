package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.attribute.Attribute;
import io.github.sculkpowered.server.attribute.AttributeModifier;
import io.github.sculkpowered.server.enchantment.Enchantment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
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
  private final byte amount;
  private final ItemMeta meta;

  private ItemStack(
      final @NotNull Material material,
      final int amount,
      final @NotNull ItemMeta meta
  ) {
    this.material = material;
    this.amount = (byte) amount;
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
    return new ItemStack(material, amount,
        new ItemMeta(CompoundBinaryTag.empty(), Map.of(), Map.of()));
  }

  @ApiStatus.Internal
  public static @NotNull ItemStack itemStack(final @NotNull Material material, final int amount,
      final CompoundBinaryTag nbt) {
    final var attributeModifiers = new HashMap<Attribute, List<AttributeModifier>>();
    for (final var tag : nbt.getList("AttributeModifiers")) {
      final var compound = (CompoundBinaryTag) tag;
      // TODO complete attribute modifiers
    }
    final var enchantments = new HashMap<Enchantment, Short>();
    for (final var tag : nbt.getList("Enchantments")) {
      final var compound = (CompoundBinaryTag) tag;
      // TODO: better registry for enchantments
      enchantments.put(Enchantment.valueOf(compound.getString("id").split(":")[1]),
          compound.getShort("lvl"));
    }
    return new ItemStack(material, amount, new ItemMeta(nbt, attributeModifiers, enchantments));
  }

  public static @NotNull ItemStack empty() {
    return EMPTY;
  }
}
