package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineZoglin extends AbstractMob implements Zoglin {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOGLIN;
    }
}
