package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEndCrystal extends AbstractEntity implements EndCrystal {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.END_CRYSTAL;
    }
}
