package de.bauhd.minecraft.server.world.block;

import java.util.Map;

public final class BlockParent {

    private MineBlockState[] states;

    public void setStates(MineBlockState[] states) {
        this.states = states;
    }

    public BlockState state(Map<String, String> properties) {
        for (final var state : this.states) {
            if (state.getProperties().equals(properties)) {
                return state;
            }
        }
        return null;
    }

    public static void set(final Map<String, BlockState> byName, final Map<Integer, BlockState> byId) {
        Blocks.set(byName, byId);
    }
}
