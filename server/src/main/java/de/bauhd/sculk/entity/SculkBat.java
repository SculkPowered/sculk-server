package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkBat extends AbstractAnimal implements Bat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BAT;
    }
}
