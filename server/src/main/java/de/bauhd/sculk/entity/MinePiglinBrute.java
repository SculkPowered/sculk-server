package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePiglinBrute extends AbstractMob implements PiglinBrute {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN_BRUTE;
    }
}