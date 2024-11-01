package eu.sculkpowered.server.attribute;

import eu.sculkpowered.server.registry.Registry;
import eu.sculkpowered.server.registry.SimpleRegistry;

public final class AttributeRegistry {

  public static Registry<Attribute> get() {
    final var registry = new SimpleRegistry<>("minecraft:attribute",
        Attributes.ARMOR);
    registry.register(Attributes.ARMOR_TOUGHNESS);
    registry.register(Attributes.ATTACK_DAMAGE);
    registry.register(Attributes.ATTACK_KNOCKBACK);
    registry.register(Attributes.ATTACK_SPEED);
    registry.register(Attributes.BLOCK_BREAK_SPEED);
    registry.register(Attributes.BLOCK_INTERACTION_RANGE);
    registry.register(Attributes.BURNING_TIME);
    registry.register(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE);
    registry.register(Attributes.ENTITY_INTERACTION_RANGE);
    registry.register(Attributes.FALL_DAMAGE_MULTIPLIER);
    registry.register(Attributes.FLYING_SPEED);
    registry.register(Attributes.FOLLOW_RANGE);
    registry.register(Attributes.GRAVITY);
    registry.register(Attributes.JUMP_STRENGTH);
    registry.register(Attributes.KNOCKBACK_RESISTANCE);
    registry.register(Attributes.LUCK);
    registry.register(Attributes.MAX_ABSORPTION);
    registry.register(Attributes.MAX_HEALTH);
    registry.register(Attributes.MINING_EFFICIENCY);
    registry.register(Attributes.MOVEMENT_EFFICIENCY);
    registry.register(Attributes.MOVEMENT_SPEED);
    registry.register(Attributes.OXYGEN_BONUS);
    registry.register(Attributes.SAFE_FALL_DISTANCE);
    registry.register(Attributes.SCALE);
    registry.register(Attributes.SNEAKING_SPEED);
    registry.register(Attributes.SPAWN_REINFORCEMENTS);
    registry.register(Attributes.STEP_HEIGHT);
    registry.register(Attributes.SUBMERGED_MINING_SPEED);
    registry.register(Attributes.SWEEPING_DAMAGE_RATIO);
    registry.register(Attributes.WATER_MOVEMENT_EFFICIENCY);
    return registry;
  }
}
