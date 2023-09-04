package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineStrider extends AbstractAnimal implements Strider {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.STRIDER;
    }
}