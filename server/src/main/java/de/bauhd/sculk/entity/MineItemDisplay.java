package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineItemDisplay extends AbstractEntity implements ItemDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ITEM_DISPLAY;
    }
}
