package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineChestBoat extends MineBoat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CHEST_BOAT;
    }
}
