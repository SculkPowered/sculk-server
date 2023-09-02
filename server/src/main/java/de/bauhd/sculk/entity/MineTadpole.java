package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTadpole extends AbstractMob implements Tadpole {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TADPOLE;
    }
}
