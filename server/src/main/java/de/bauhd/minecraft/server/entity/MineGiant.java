package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGiant extends AbstractMob implements Giant {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GIANT;
    }
}
