package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEgg extends AbstractEntity implements Egg {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EGG;
    }
}
