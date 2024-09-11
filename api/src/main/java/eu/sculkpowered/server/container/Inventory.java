package eu.sculkpowered.server.container;

import eu.sculkpowered.server.container.equipment.EntityEquipment;

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
