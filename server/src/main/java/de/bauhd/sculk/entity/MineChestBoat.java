package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineChestBoat extends MineBoat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CHEST_BOAT;
    }
}
