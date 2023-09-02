package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBlockDisplay extends AbstractEntity implements BlockDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BLOCK_DISPLAY;
    }
}
