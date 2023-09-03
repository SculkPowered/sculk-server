package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkGoat extends AbstractAnimal implements Goat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GOAT;
    }
}
