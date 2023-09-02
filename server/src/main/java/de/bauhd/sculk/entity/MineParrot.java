package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineParrot extends AbstractTameableAnimal implements Parrot {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PARROT;
    }
}
