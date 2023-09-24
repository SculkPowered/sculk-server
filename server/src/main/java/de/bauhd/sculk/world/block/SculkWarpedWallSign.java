package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedWallSign extends SculkBlockState implements WarpedWallSign {

    SculkWarpedWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}