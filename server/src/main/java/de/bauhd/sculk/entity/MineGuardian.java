package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGuardian extends AbstractMob implements Guardian {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GUARDIAN;
    }
}
