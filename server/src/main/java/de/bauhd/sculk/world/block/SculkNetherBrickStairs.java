package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkNetherBrickStairs extends SculkBlockState implements NetherBrickStairs {

    SculkNetherBrickStairs(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}