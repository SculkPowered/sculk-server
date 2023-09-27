package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkCalibratedSculkSensor extends SculkBlockState implements CalibratedSculkSensor {

    SculkCalibratedSculkSensor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}