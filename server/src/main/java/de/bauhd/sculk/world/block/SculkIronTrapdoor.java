package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkIronTrapdoor extends SculkBlockState implements IronTrapdoor {

    SculkIronTrapdoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}