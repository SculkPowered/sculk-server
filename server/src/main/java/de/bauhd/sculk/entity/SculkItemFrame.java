package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkItemFrame extends AbstractEntity implements ItemFrame {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ITEM_FRAME;
    }
}
