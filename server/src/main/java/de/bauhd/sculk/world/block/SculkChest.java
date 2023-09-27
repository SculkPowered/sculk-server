package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkChest extends SculkBlockState implements Chest {

    SculkChest(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}