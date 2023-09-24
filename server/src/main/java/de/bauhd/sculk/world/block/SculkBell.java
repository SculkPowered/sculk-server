package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBell extends SculkBlockState implements Bell {

    SculkBell(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}