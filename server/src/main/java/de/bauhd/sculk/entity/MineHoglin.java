package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineHoglin extends AbstractAnimal implements Hoglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HOGLIN;
    }
}
