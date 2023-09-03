package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkZoglin extends AbstractMob implements Zoglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOGLIN;
    }
}
