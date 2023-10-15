package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkWallFan extends SculkBlockState implements WallFan {

  SculkWallFan(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}