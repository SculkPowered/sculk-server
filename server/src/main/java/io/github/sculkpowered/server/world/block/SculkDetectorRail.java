package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkDetectorRail extends SculkBlockState implements DetectorRail {

  SculkDetectorRail(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}