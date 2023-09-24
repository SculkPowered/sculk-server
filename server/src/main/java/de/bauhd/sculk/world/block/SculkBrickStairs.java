package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBrickStairs extends SculkBlockState implements BrickStairs {

    SculkBrickStairs(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}