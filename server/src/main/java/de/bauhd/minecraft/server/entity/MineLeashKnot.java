package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineLeashKnot extends AbstractEntity implements LeashKnot {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LEASH_KNOT;
    }
}
