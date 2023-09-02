package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePiglin extends AbstractMob implements Piglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN;
    }
}
