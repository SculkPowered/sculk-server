package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkTrapdoor extends SculkBlockState implements Trapdoor {

    SculkTrapdoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}