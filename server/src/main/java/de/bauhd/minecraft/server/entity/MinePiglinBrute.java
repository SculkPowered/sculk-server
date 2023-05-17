package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePiglinBrute extends AbstractMob implements PiglinBrute {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN_BRUTE;
    }
}
