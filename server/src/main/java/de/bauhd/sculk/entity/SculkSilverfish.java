package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSilverfish extends AbstractMob implements Silverfish {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SILVERFISH;
    }
}
