package de.bauhd.sculk.world.block;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MineBlockState implements BlockState {

    private final BlockParent block;
    private final int id;
    private final Map<String, String> properties;

    public MineBlockState(final BlockParent block, final int id, final Map<String, String> properties) {
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
    public boolean isPowered() {
        return Objects.equals(this.properties.get("powered"), "true");
    }

    @Override
    public BlockState powered(boolean powered) {
        return this.property("powered", powered);
    }

    @Override
    public boolean isLit() {
        return Objects.equals(this.properties.get("powered"), "lit");
    }

    @Override
    public BlockState lit(boolean lit) {
        return this.property("lit", lit);
    }

    @Override
    public BlockState face(Block.@NotNull Face face) {
        return this.property("face", face.name().toLowerCase());
    }

    @Override
    public BlockState facing(Block.@NotNull Facing facing) {
        return this.property("facing", facing.name().toLowerCase());
    }

    @Override
    public BlockState half(Block.@NotNull Half half) {
        return this.property("half", half.name().toLowerCase());
    }

    @Override
    public boolean isOpen() {
        return Objects.equals(this.properties.get("open"), "true");
    }

    @Override
    public BlockState open(boolean open) {
        return this.property("open", open);
    }

    @Override
    public boolean isWaterlogged() {
        return Objects.equals(this.properties.get("waterlogged"), "true");
    }

    @Override
    public BlockState waterlogged(boolean waterlogged) {
        return this.property("waterlogged", waterlogged);
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
    public BlockState properties(@NotNull Map<String, String> properties) {
        final var newProperties = new HashMap<>(this.properties);
        newProperties.putAll(properties);
        return this.block.state(newProperties);
    }

    @Override
    public String toString() {
        return "MineBlockState{" +
                "block=" + this.block +
                ", id=" + this.id +
                ", properties=" + this.properties +
                '}';
    }
}
