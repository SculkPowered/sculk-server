package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWolf extends AbstractTameableAnimal implements Wolf {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WOLF;
    }
}
