package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an inventory of a player.
 */
public interface Inventory extends Container {

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getItemInMainHand();

  /**
   * @since 1.0.0
   */
  void setItemInMainHand(@NotNull ItemStack item);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getItemInOffHand();

  /**
   * @since 1.0.0
   */
  void setItemInOffHand(@NotNull ItemStack item);

  /**
   * @since 1.0.0
   */
  void setHelmet(@NotNull ItemStack helmet);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getHelmet();

  /**
   * @since 1.0.0
   */
  void setChestplate(@NotNull ItemStack chestplate);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getChestplate();

  /**
   * @since 1.0.0
   */
  void setLeggings(@NotNull ItemStack leggings);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getLeggings();

  /**
   * @since 1.0.0
   */
  void setBoots(@NotNull ItemStack boots);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack getBoots();
}
