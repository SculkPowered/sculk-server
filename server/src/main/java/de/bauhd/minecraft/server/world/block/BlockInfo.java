package de.bauhd.minecraft.server.world.block;

import java.util.List;
import java.util.Map;

public final class BlockInfo {

    private final String namespace;
    private final Map<String, Property<?>> properties;
    private final List<BlockState> states;

    public BlockInfo(final String namespace, final Map<String, Property<?>> properties, final List<BlockState> states) {
        this.namespace = namespace;
        this.properties = properties;
        this.states = states;
    }
}
