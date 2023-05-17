package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineMinecart extends AbstractEntity implements Minecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MINECART;
    }
}
