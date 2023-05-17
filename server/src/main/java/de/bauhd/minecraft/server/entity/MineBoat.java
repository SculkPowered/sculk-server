package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public class MineBoat extends AbstractEntity implements Boat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BOAT;
    }
}
