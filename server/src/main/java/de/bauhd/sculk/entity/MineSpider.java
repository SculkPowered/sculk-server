package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSpider extends AbstractMob implements Spider {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SPIDER;
    }
}
