package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePanda extends AbstractAnimal implements Panda {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PANDA;
    }
}
