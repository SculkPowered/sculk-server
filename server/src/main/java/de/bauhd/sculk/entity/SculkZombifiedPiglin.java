package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZombifiedPiglin extends SculkZombie implements ZombifiedPiglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIFIED_PIGLIN;
    }
}
