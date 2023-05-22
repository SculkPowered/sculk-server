package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSquid extends AbstractAnimal implements Squid {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SQUID;
    }
}