package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEndermite extends AbstractMob implements Endermite {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMITE;
    }
}
