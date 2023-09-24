package de.bauhd.sculk.world.block;

import java.util.Map;

public class SculkBambooButton extends SculkBlockState implements BambooButton {

    SculkBambooButton(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }
}