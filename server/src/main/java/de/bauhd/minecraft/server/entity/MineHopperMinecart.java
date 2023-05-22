package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHopperMinecart extends AbstractEntity implements HopperMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HOPPER_MINECART;
    }
}