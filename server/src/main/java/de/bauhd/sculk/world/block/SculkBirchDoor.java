package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBirchDoor extends SculkBlockState implements BirchDoor {

    SculkBirchDoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}