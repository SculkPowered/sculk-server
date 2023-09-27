package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkTrappedChest extends SculkBlockState implements TrappedChest {

    SculkTrappedChest(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}