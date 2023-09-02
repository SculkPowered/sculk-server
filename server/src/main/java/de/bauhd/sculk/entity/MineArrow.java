package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class MineArrow extends AbstractEntity implements Arrow {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ARROW;
    }
}
