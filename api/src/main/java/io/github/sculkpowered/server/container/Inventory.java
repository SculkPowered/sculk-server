package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.equipment.EntityEquipment;

/**
 * Represents an inventory of a player.
 */
public interface Inventory extends Container, EntityEquipment {

  /**
   * Resends the full inventory to the player.
   *
   * @since 1.0.0
   */
  void resend();
}
