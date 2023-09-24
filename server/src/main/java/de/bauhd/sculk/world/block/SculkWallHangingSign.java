package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWallHangingSign extends SculkBlockState implements WallHangingSign {

    SculkWallHangingSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}