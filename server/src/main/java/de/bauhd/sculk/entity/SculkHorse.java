package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkHorse extends AbstractAnimal implements Horse {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.HORSE;
    }
}
