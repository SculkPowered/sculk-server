package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedFenceGate extends SculkBlockState implements WarpedFenceGate {

    SculkWarpedFenceGate(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}