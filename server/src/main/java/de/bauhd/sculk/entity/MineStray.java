package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineStray extends MineSkeleton implements Stray {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.STRAY;
    }
}
