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
  @NotNull ItemStack itemInMainHand();

  /**
   * @since 1.0.0
   */
  void itemInMainHand(@NotNull ItemStack item);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack itemInOffHand();

  /**
   * @since 1.0.0
   */
  void itemInOffHand(@NotNull ItemStack item);

  /**
   * @since 1.0.0
   */
  void helmet(@NotNull ItemStack helmet);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack helmet();

  /**
   * @since 1.0.0
   */
  void chestplate(@NotNull ItemStack chestplate);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack chestplate();

  /**
   * @since 1.0.0
   */
  void leggings(@NotNull ItemStack leggings);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack leggings();

  /**
   * @since 1.0.0
   */
  void boots(@NotNull ItemStack boots);

  /**
   * @since 1.0.0
   */
  @NotNull ItemStack boots();

  /**
   * Resends the full inventory to the player.
   *
   * @since 1.0.0
   */
  void resend();
}
