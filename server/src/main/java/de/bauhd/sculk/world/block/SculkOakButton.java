package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkOakButton extends SculkBlockState implements OakButton {

    SculkOakButton(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}