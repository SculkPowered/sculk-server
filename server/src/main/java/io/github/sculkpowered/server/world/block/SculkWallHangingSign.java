package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkWallHangingSign extends SculkBlockState implements WallHangingSign {

  SculkWallHangingSign(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}