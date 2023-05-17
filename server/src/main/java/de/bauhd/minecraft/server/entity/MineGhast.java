package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGhast extends AbstractMob implements Ghast {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GHAST;
    }
}
