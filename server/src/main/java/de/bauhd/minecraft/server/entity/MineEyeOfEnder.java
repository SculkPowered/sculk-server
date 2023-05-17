package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEyeOfEnder extends AbstractEntity implements EyeOfEnder {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.EYE_OF_ENDER;
    }
}
