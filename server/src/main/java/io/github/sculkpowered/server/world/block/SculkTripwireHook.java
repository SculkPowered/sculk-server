package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkTripwireHook extends SculkBlockState implements TripwireHook {

  SculkTripwireHook(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}