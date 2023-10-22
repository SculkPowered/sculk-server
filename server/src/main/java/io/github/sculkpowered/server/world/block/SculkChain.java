package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkChain extends SculkBlockState implements Chain {

  SculkChain(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}