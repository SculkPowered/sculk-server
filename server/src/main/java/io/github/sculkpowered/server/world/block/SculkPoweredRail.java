package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkPoweredRail extends SculkBlockState implements PoweredRail {

  SculkPoweredRail(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}