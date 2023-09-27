package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkLever extends SculkBlockState implements Lever {

    SculkLever(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}