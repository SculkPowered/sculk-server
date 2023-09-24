package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedStairs extends SculkBlockState implements WarpedStairs {

    SculkWarpedStairs(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}