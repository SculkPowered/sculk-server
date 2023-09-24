package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkPitcherCrop extends SculkBlockState implements PitcherCrop {

    SculkPitcherCrop(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}