package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSalmon extends AbstractMob implements Salmon {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SALMON;
    }
}
