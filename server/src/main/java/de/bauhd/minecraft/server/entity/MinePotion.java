package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePotion extends AbstractEntity implements Potion {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.POTION;
    }
}
