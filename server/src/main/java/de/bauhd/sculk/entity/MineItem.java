package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineItem extends AbstractEntity implements Item {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ITEM;
    }
}
