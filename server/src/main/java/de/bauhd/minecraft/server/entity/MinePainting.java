package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePainting extends AbstractEntity implements Painting {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PAINTING;
    }
}
