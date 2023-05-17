package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePillager extends AbstractMob implements Pillager {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PILLAGER;
    }
}
