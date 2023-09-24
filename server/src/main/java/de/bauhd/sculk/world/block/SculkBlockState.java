package de.bauhd.sculk.world.block;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class SculkBlockState implements BlockState {

    protected final BlockParent block;
    protected final int id;
    protected final Map<String, String> properties;

    SculkBlockState(final BlockParent block, final int id, final Map<String, String> properties) {
        this.block = block;
        this.id = id;
        this.properties = properties;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public @NotNull Map<String, String> getProperties() {
        return this.properties;
    }

    @Override
    public boolean hasProperty(@NotNull String key) {
        return this.properties.containsKey(key);
    }

    @Override
    public BlockState property(@NotNull String key, @NotNull String value) {
        final var properties = new HashMap<>(this.properties);
        properties.put(key, value);
        return this.block.state(properties);
    }

    @Override
    public BlockState property(@NotNull String key, boolean value) {
        return this.property(key, String.valueOf(value));
    }

    @Override
    public BlockState property(@NotNull String key, int value) {
        return this.property(key, String.valueOf(value));
    }

    @Override
    public BlockState properties(@NotNull Map<String, String> properties) {
        final var newProperties = new HashMap<>(this.properties);
        newProperties.putAll(properties);
        return this.block.state(newProperties);
    }

    @Override
    public String toString() {
        return "SculkBlockState{" +
                "block=" + this.block +
                ", id=" + this.id +
                ", properties=" + this.properties +
                '}';
    }

    public static class Waterloggable<T extends BlockState> extends SculkBlockState implements BlockState.Waterloggable<T> {

        public Waterloggable(BlockParent block, int id, Map<String, String> properties) {
            super(block, id, properties);
        }
    }

    public static class Powerable<T extends BlockState> extends SculkBlockState implements BlockState.Powerable<T> {

        public Powerable(BlockParent block, int id, Map<String, String> properties) {
            super(block, id, properties);
        }
    }
}
