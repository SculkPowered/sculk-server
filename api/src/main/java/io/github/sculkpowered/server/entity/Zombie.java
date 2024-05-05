package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.attribute.Attribute;

/**
 * Represents a zombie entity.
 */
public interface Zombie extends Mob, Ageable {

  Attribute SPAWN_REINFORCEMENTS = new Attribute("zombie.spawn_reinforcements", 16, 0F, 1F);
}