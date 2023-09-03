package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkMinecart extends AbstractEntity implements Minecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MINECART;
    }
}
