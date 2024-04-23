package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a container of items.
 */
public interface Container {

  /**
   * Gets the title of the container.
   *
   * @return the title of the container
   * @since 1.0.0
   */
  @NotNull Component title();

  /**
   * Gets the type of the container
   *
   * @return the type of the container
   * @since 1.0.0
   */
  @NotNull Type type();

  /**
   * Sets the item at the specified index.
   *
   * @param index the index of the item
   * @param item  the item to set
   * @since 1.0.0
   */
  void item(int index, @NotNull ItemStack item);

  /**
   * Gets an item by its index.
   *
   * @param index the index of the item
   * @return the item at the specified index
   * @since 1.0.0
   */
  @NotNull ItemStack item(int index);

  /**
   * Gets the underlying list of items.
   *
   * @return the list of items
   * @since 1.0.0
   */
  @NotNull List<ItemStack> items();

  /**
   * All possible types a container can have.
   */
  enum Type {

    GENERIC_9x1(9),
    GENERIC_9x2(18),
    GENERIC_9x3(27),
    GENERIC_9x4(36),
    GENERIC_9x5(45),
    GENERIC_9x6(54),
    GENERIC_3x3(9),
    ANVIL(3),
    BEACON(1),
    BLAST_FURNACE(3),
    BREWING_STAND(5),
    CRAFTING(10),
    ENCHANTMENT(2),
    FURNACE(3),
    GRINDSTONE(3),
    HOPPER(5),
    LECTERN(1),
    LOOM(4),
    MERCHANT(3),
    SHULKER_BOX(27),
    SMITHING(3),
    SMOKER(3),
    CARTOGRAPHY(3),
    STONECUTTER(2),
    PLAYER(46);

    private final int size;

    Type(final int size) {
      this.size = size;
    }

    /**
     * Gets the size of a container of this type.
     *
     * @return the size of the container type
     */
    public int size() {
      return this.size;
    }
  }
}
