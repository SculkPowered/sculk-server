package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTrident extends MineArrow implements Trident {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TRIDENT;
    }
}
