package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkActivatorRail extends SculkBlockState implements ActivatorRail {

    SculkActivatorRail(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}