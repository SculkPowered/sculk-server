package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineAllay extends AbstractEntity implements Allay {
    @Override
    public @NotNull EntityType getType() {
        return EntityType.ALLAY;
    }
}
