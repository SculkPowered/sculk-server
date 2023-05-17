package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MinePufferfish extends AbstractMob implements Pufferfish {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PUFFERFISH;
    }
}
