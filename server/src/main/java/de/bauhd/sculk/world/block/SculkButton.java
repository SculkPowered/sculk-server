package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkButton extends SculkBlockState implements Button {

  SculkButton(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties);
  }
}