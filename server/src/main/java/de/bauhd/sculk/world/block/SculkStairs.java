package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkStairs extends SculkBlockState implements Stairs {

    SculkStairs(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}