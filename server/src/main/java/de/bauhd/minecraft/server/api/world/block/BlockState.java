package de.bauhd.minecraft.server.api.world.block;

import java.util.Map;

public final class BlockState {

    private final BlockInfo blockInfo;
    private final Map<String, Property<?>> properties;

    public BlockState(final BlockInfo blockInfo, final Map<String, Property<?>> properties) {
        this.blockInfo = blockInfo;
        this.properties = properties;
    }
}
