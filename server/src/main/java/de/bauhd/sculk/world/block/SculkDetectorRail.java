package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkDetectorRail extends SculkBlockState implements DetectorRail {

    SculkDetectorRail(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}