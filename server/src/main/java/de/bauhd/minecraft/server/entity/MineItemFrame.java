package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public class MineItemFrame extends AbstractEntity implements ItemFrame {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ITEM_FRAME;
    }
}
