package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.attribute.Attribute;

/**
 * Represents a horse entity.
 */
public interface Horse extends Animal {

  Attribute JUMP_STRENGTH = new Attribute("horse.jump_strength", 9, 0.7F, 2F);
}