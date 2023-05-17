package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineChestMinecart extends AbstractEntity implements ChestMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CHEST_MINECART;
    }
}
