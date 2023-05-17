package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineAreaEffectCloud extends AbstractEntity implements AreaEffectCloud {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.AREA_EFFECT_CLOUD;
    }
}
