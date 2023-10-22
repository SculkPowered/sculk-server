package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkTrapdoor extends SculkBlockState implements Trapdoor {

  SculkTrapdoor(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}