package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineZombifiedPiglin extends MineZombie implements ZombifiedPiglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIFIED_PIGLIN;
    }
}
