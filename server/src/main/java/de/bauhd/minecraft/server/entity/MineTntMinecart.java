package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTntMinecart extends AbstractEntity implements TntMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TNT_MINECART;
    }
}
