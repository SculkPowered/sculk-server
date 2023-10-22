package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkDoor extends SculkBlockState implements Door {

  SculkDoor(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}