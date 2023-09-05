package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkDrowned extends SculkZombie implements Drowned {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROWNED;
    }
}
