package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkStrider extends AbstractAnimal implements Strider {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.STRIDER;
    }
}
