package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineElderGuardian extends AbstractMob implements ElderGuardian {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GUARDIAN;
    }
}
