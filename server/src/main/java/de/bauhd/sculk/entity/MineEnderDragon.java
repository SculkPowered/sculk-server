package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEnderDragon extends AbstractMob implements EnderDragon {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }
}
