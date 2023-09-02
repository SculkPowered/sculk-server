package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineTraderLlama extends MineLLama implements TraderLlama {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TRADER_LLAMA;
    }
}
