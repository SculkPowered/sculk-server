package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkHangingSign extends SculkBlockState implements HangingSign {

    SculkHangingSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}