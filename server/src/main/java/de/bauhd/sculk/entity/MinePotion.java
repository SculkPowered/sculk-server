package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePotion extends AbstractEntity implements Potion {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.POTION;
    }
}
