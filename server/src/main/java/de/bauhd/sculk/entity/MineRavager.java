package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineRavager extends AbstractMob implements Ravager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.RAVAGER;
    }
}
