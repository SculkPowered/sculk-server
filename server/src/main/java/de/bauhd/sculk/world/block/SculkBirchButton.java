package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBirchButton extends SculkBlockState implements BirchButton {

    SculkBirchButton(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}