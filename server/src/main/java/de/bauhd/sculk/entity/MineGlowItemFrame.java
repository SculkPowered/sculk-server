package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineGlowItemFrame extends MineItemFrame implements GlowItemFrame {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GLOW_ITEM_FRAME;
    }
}
