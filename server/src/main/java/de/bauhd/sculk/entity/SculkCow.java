package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkCow extends AbstractAnimal implements Cow {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.COW;
    }
}
