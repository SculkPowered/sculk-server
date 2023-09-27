package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkDecoratedPot extends SculkBlockState implements DecoratedPot {

    SculkDecoratedPot(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}