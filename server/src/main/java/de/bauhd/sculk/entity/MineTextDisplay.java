package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTextDisplay extends AbstractEntity implements TextDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TEXT_DISPLAY;
    }
}
