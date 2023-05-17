package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTextDisplay extends AbstractEntity implements TextDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TEXT_DISPLAY;
    }
}
