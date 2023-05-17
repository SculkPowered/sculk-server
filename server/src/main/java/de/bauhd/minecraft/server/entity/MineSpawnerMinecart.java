package de.bauhd.minecraft.server.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSpawnerMinecart extends AbstractEntity implements SpawnerMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SPAWNER_MINECART;
    }
}
