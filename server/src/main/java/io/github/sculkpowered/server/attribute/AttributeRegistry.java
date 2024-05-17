package io.github.sculkpowered.server.attribute;

import io.github.sculkpowered.server.entity.Zombie;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;

public final class AttributeRegistry {

  public static Registry<Attribute> get() {
    final var registry = new SimpleRegistry<>("minecraft:attribute",
        GenericAttribute.ARMOR);
    registry.register(GenericAttribute.ARMOR_TOUGHNESS);
    registry.register(GenericAttribute.ATTACK_DAMAGE);
    registry.register(GenericAttribute.ATTACK_KNOCKBACK);
    registry.register(GenericAttribute.ATTACK_SPEED);
    registry.register(Player.BLOCK_BREAKING_SPEED);
    registry.register(Player.BLOCK_INTERACTION_RANGE);
    registry.register(Player.ENTITY_INTERACTION_RANGE);
    registry.register(GenericAttribute.FALL_DAMAGE_MULTIPLIER);
    registry.register(GenericAttribute.FLYING_SPEED);
    registry.register(GenericAttribute.FOLLOW_RANGE);
    registry.register(GenericAttribute.GRAVITY);
    registry.register(GenericAttribute.JUMP_STRENGTH);
    registry.register(GenericAttribute.KNOCKBACK_RESISTANCE);
    registry.register(GenericAttribute.LUCK);
    registry.register(GenericAttribute.MAX_ABSORPTION);
    registry.register(GenericAttribute.MAX_HEALTH);
    registry.register(GenericAttribute.MOVEMENT_SPEED);
    registry.register(GenericAttribute.SAFE_FALL_DISTANCE);
    registry.register(GenericAttribute.SCALE);
    registry.register(Zombie.SPAWN_REINFORCEMENTS);
    registry.register(GenericAttribute.STEP_HEIGHT);
    return registry;
  }
}
