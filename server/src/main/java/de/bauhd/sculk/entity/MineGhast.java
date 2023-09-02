package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGhast extends AbstractMob implements Ghast {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GHAST;
    }
}
