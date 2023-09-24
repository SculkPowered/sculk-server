package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkStoneButton extends SculkBlockState implements StoneButton {

    SculkStoneButton(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}