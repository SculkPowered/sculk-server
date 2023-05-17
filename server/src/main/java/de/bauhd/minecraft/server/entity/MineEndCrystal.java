package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEndCrystal extends AbstractEntity implements EndCrystal {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.END_CRYSTAL;
    }
}
