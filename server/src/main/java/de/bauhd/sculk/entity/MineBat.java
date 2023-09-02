package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBat extends AbstractAnimal implements Bat {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BAT;
    }
}
