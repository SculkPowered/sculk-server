package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFurnaceMinecart extends AbstractEntity implements FurnaceMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FURNACE_MINECART;
    }
}
