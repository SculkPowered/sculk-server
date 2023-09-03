package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkAreaEffectCloud extends AbstractEntity implements AreaEffectCloud {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.AREA_EFFECT_CLOUD;
    }
}
