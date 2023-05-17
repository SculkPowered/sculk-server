package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineFallingBlock extends AbstractEntity implements FallingBlock {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FALLING_BLOCK;
    }
}
