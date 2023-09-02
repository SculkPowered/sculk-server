package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineMule extends AbstractAnimal implements Mule {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MULE;
    }
}
