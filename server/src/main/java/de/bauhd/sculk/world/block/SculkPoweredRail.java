package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkPoweredRail extends SculkBlockState implements PoweredRail {

    SculkPoweredRail(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }

}