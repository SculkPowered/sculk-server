package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkFenceGate extends SculkBlockState implements FenceGate {

    SculkFenceGate(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}