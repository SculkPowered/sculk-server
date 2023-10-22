package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkLadder extends SculkBlockState implements Ladder {

  SculkLadder(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}