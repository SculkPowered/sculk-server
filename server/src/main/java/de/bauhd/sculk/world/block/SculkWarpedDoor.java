package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedDoor extends SculkBlockState implements WarpedDoor {

    SculkWarpedDoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}