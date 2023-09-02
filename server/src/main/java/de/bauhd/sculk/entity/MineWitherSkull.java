package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWitherSkull extends AbstractEntity implements WitherSkull {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITHER_SKULL;
    }
}
