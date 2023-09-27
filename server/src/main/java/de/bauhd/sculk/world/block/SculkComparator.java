package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkComparator extends SculkBlockState implements Comparator {

    SculkComparator(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}