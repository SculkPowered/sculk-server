package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkFenceGate extends SculkBlockState implements FenceGate {

  SculkFenceGate(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}