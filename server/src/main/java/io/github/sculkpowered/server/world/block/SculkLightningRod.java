package io.github.sculkpowered.server.world.block;

import java.util.Map;

final class SculkLightningRod extends SculkBlockState implements LightningRod {

  SculkLightningRod(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}