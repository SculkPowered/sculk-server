package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSkulkerBullet extends AbstractEntity implements ShulkerBullet {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHULKER_BULLET;
    }
}
