package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineLightningBolt extends AbstractEntity {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LIGHTNING_BOLT;
    }
}
