package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTadpole extends AbstractMob implements Tadpole {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TADPOLE;
    }
}
