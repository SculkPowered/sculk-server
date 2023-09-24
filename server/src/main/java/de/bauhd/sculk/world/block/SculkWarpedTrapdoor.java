package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedTrapdoor extends SculkBlockState implements WarpedTrapdoor {

    SculkWarpedTrapdoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }

}