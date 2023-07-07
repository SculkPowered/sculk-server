package de.bauhd.minecraft.server.event.block;

import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.event.ResultedEvent;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class BlockPlaceEvent extends ResultedEvent<ResultedEvent.GenericResult> {

    private final @NotNull Player player;
    private final @NotNull Position position;
    private final @NotNull BlockState block;

    public BlockPlaceEvent(@NotNull Player player, @NotNull Position position, @NotNull BlockState block) {
        this.player = player;
        this.position = position;
        this.block = block;
        this.result = GenericResult.allowed();
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull Position getPosition() {
        return this.position;
    }

    public @NotNull BlockState getBlock() {
        return this.block;
    }
}
