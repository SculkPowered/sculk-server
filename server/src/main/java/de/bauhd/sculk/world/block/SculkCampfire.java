package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkCampfire extends SculkBlockState implements Campfire {

    SculkCampfire(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}