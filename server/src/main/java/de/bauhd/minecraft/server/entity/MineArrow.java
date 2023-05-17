package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public class MineArrow extends AbstractEntity implements Arrow {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ARROW;
    }
}
