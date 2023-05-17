package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGuardian extends AbstractMob implements Guardian {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GUARDIAN;
    }
}
