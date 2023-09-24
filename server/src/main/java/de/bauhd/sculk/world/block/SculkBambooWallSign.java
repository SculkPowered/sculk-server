package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBambooWallSign extends SculkBlockState implements BambooWallSign {

    SculkBambooWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}