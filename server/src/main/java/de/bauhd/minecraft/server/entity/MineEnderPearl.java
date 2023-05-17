package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineEnderPearl extends AbstractEntity implements EnderPearl {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDER_PEARL;
    }
}
