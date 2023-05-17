package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineWitherSkull extends AbstractEntity implements WitherSkull {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITHER_SKULL;
    }
}
