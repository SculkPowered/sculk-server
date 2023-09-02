package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineSpawnerMinecart extends AbstractEntity implements SpawnerMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SPAWNER_MINECART;
    }
}
