package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineBlockDisplay extends AbstractEntity implements BlockDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BLOCK_DISPLAY;
    }
}
