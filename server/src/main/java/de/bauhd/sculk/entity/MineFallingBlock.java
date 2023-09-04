package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFallingBlock extends AbstractEntity implements FallingBlock {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FALLING_BLOCK;
    }
}