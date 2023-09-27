package de.bauhd.sculk.world.block;

import java.util.Map;

final class SculkEnderChest extends SculkBlockState implements EnderChest {

    SculkEnderChest(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}