package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineElderGuardian extends AbstractMob implements ElderGuardian {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GUARDIAN;
    }
}
