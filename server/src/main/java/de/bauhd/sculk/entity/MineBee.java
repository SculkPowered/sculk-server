package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBee extends AbstractAnimal implements Bee {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BEE;
    }
}
