package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkDecoratedPot extends SculkBlockState implements DecoratedPot {

    SculkDecoratedPot(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}