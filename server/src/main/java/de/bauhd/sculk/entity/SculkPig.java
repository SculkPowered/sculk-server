package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkPig extends AbstractAnimal implements Pig {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIG;
    }
}
