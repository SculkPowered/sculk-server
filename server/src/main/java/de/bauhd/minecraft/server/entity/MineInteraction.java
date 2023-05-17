package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineInteraction extends AbstractEntity implements Interaction {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.INTERACTION;
    }
}
