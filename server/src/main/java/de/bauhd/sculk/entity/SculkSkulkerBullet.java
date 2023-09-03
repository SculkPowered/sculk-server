package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSkulkerBullet extends AbstractEntity implements ShulkerBullet {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHULKER_BULLET;
    }
}
