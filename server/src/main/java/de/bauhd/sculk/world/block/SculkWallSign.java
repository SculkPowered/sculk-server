package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWallSign extends SculkBlockState implements WallSign {

    SculkWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}