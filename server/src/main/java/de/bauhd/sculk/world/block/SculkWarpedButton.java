package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkWarpedButton extends SculkBlockState implements WarpedButton {

    SculkWarpedButton(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}