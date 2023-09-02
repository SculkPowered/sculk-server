package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineLlamaSpit extends AbstractEntity implements LlamaSpit {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LLAMA_SPIT;
    }
}
