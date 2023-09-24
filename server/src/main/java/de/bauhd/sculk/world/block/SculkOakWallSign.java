package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkOakWallSign extends SculkBlockState implements OakWallSign {

    SculkOakWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}