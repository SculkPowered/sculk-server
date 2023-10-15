package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkRepeater extends SculkBlockState implements Repeater {

  SculkRepeater(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}