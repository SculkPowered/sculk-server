package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkGrindstone extends SculkBlockState implements Grindstone {

    SculkGrindstone(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}