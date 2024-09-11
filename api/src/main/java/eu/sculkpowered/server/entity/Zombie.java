package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.attribute.Attribute;

/**
 * Represents a zombie entity.
 */
public interface Zombie extends Mob, Ageable {

  Attribute SPAWN_REINFORCEMENTS =
      new Attribute("zombie.spawn_reinforcements", 26, 0F, 0F, 1F);
}