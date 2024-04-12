package io.github.sculkpowered.server.damage;

import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;

public final class DamageTypeRegistry {

  public static Registry<DamageType> get() {
    final var registry = new SimpleRegistry<>("minecraft:damage_type", DamageType.OUT_OF_WORLD);
    // START
    registry.register(DamageType.ARROW);
    registry.register(DamageType.BAD_RESPAWN_POINT);
    registry.register(DamageType.CACTUS);
    registry.register(DamageType.CRAMMING);
    registry.register(DamageType.DRAGON_BREATH);
    registry.register(DamageType.DROWN);
    registry.register(DamageType.DRY_OUT);
    registry.register(DamageType.EXPLOSION);
    registry.register(DamageType.FALL);
    registry.register(DamageType.FALLING_ANVIL);
    registry.register(DamageType.FALLING_BLOCK);
    registry.register(DamageType.FALLING_STALACTITE);
    registry.register(DamageType.FIREBALL);
    registry.register(DamageType.FIREWORKS);
    registry.register(DamageType.FLY_INTO_WALL);
    registry.register(DamageType.FREEZE);
    registry.register(DamageType.GENERIC);
    registry.register(DamageType.GENERIC_KILL);
    registry.register(DamageType.HOT_FLOOR);
    registry.register(DamageType.INDIRECT_MAGIC);
    registry.register(DamageType.IN_FIRE);
    registry.register(DamageType.IN_WALL);
    registry.register(DamageType.LAVA);
    registry.register(DamageType.LIGHTNING_BOLT);
    registry.register(DamageType.MAGIC);
    registry.register(DamageType.MOB_ATTACK);
    registry.register(DamageType.MOB_ATTACK_NO_AGGRO);
    registry.register(DamageType.MOB_PROJECTILE);
    registry.register(DamageType.ON_FIRE);
    registry.register(DamageType.OUTSIDE_BORDER);
    registry.register(DamageType.OUT_OF_WORLD);
    registry.register(DamageType.PLAYER_ATTACK);
    registry.register(DamageType.PLAYER_EXPLOSION);
    registry.register(DamageType.SONIC_BOOM);
    registry.register(DamageType.SPIT);
    registry.register(DamageType.STALAGMITE);
    registry.register(DamageType.STARVE);
    registry.register(DamageType.STING);
    registry.register(DamageType.SWEET_BERRY_BUSH);
    registry.register(DamageType.THORNS);
    registry.register(DamageType.THROWN);
    registry.register(DamageType.TRIDENT);
    registry.register(DamageType.UNATTRIBUTED_FIREBALL);
    registry.register(DamageType.WITHER);
    registry.register(DamageType.WITHER_SKULL);
    // END
    return registry;
  }
}
