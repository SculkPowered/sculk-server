package de.bauhd.sculk.world.block;

import java.util.Map;

public final class BlockParent {

    private final String name;
    private SculkBlockState[] states;

    public BlockParent(final String name) {
        this.name = name;
    }

    public void setStates(SculkBlockState[] states) {
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

    @Override
    public String toString() {
        return "BlockParent{" +
                "name=" + this.name +
                '}';
    }
}
