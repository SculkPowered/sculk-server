package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineCommandBlockMinecart extends AbstractEntity implements CommandBlockMinecart {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.COMMAND_BLOCK_MINECART;
    }
}
