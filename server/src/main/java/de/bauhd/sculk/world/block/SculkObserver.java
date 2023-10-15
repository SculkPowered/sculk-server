package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkObserver extends SculkBlockState implements Observer {

  SculkObserver(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}