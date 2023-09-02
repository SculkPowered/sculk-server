package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineDolphin extends AbstractMob implements Dolphin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DOLPHIN;
    }
}
