package de.bauhd.minecraft.server.world.block;

import net.kyori.adventure.key.Key;

public interface Block {

    Key AIR = Key.key("air");

    enum Face {

        BOTTOM,
        TOP,
        NORTH,
        SOUTH,
        WEST,
        EAST

    }

}
