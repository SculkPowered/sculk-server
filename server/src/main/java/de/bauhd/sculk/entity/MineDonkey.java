package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDonkey extends AbstractAnimal implements Donkey {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DONKEY;
    }
}
