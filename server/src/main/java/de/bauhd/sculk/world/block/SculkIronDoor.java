package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkIronDoor extends SculkBlockState implements IronDoor {

    SculkIronDoor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}