package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTnt extends AbstractEntity implements Tnt {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TNT;
    }
}
