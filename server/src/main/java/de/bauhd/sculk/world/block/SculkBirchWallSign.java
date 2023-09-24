package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBirchWallSign extends SculkBlockState implements BirchWallSign {

    SculkBirchWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}