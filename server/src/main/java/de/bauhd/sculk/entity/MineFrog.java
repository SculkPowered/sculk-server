package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFrog extends AbstractAnimal implements Frog {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FROG;
    }
}
