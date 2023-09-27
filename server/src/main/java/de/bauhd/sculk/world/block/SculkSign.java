package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkSign extends SculkBlockState implements Sign {

    SculkSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}