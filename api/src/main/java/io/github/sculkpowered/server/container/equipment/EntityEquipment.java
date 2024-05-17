package io.github.sculkpowered.server.container.equipment;

import io.github.sculkpowered.server.container.item.ItemStack;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the equipment of an entity.
 */
public interface EntityEquipment {

  /**
   * Gets the equipment at the specified equipment slot.
   * @param slot the slot
   * @return the item at the specified equipment slot
   * @since 1.0.0
   */
  @NotNull ItemStack get(@NotNull EquipmentSlot slot);

  /**
   * Sets the equipment at the specified equipment slot.
   * @param slot the slot
   * @param equipment the item to set
   * @return the previous item
   * @since 1.0.0
   */
  @NotNull ItemStack set(@NotNull EquipmentSlot slot, @NotNull ItemStack equipment);

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack itemInMainHand() {
    return this.get(EquipmentSlot.MAIN_HAND);
  }

  /**
   * @since 1.0.0
   */
  default void itemInMainHand(@NotNull ItemStack item) {
    this.set(EquipmentSlot.MAIN_HAND, item);
  }

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack itemInOffHand() {
    return this.get(EquipmentSlot.OFF_HAND);
  }

  /**
   * @since 1.0.0
   */
  default void itemInOffHand(@NotNull ItemStack item) {
    this.set(EquipmentSlot.OFF_HAND, item);
  }

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack helmet() {
    return this.get(EquipmentSlot.HEAD);
  }

  /**
   * @since 1.0.0
   */
  default void helmet(@NotNull ItemStack item) {
    this.set(EquipmentSlot.HEAD, item);
  }

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack chestplate() {
    return this.get(EquipmentSlot.CHEST);
  }

  /**
   * @since 1.0.0
   */
  default void chestplate(@NotNull ItemStack item) {
    this.set(EquipmentSlot.CHEST, item);
  }

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack leggings() {
    return this.get(EquipmentSlot.LEGS);
  }

  /**
   * @since 1.0.0
   */
  default void leggings(@NotNull ItemStack item) {
    this.set(EquipmentSlot.LEGS, item);
  }

  /**
   * @since 1.0.0
   */
  default @NotNull ItemStack boots() {
    return this.get(EquipmentSlot.FEET);
  }

  /**
   * @since 1.0.0
   */
  default void boots(@NotNull ItemStack item) {
    this.set(EquipmentSlot.FEET, item);
  }

  /**
   * Gets the equipment as a map.
   * @return a map that contains the equipment, empty stacks are not stored
   */
  Map<EquipmentSlot, ItemStack> asMap();
}
