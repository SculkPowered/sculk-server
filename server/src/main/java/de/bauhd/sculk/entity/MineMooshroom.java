package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineMooshroom extends AbstractAnimal implements Mooshroom {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MOOSHROOM;
    }
}
