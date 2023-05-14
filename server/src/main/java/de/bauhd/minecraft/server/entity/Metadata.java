package de.bauhd.minecraft.server.entity;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.world.Position;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;

public final class Metadata {

    private Map<Integer, Entry<?>> changes = new HashMap<>();

    public void setByte(final int index, final byte value) {
        this.changes.put(index, new Entry<>(0, value, Buffer::writeByte));
    }

    public void setVarInt(final int index, final int value) {
        this.changes.put(index, new Entry<>(1, value, Buffer::writeVarInt));
    }

    public void setVarLong(final int index, final long value) {
        this.changes.put(index, new Entry<>(2, value, Buffer::writeVarLong));
    }

    public void setFloat(final int index, final float value) {
        this.changes.put(index, new Entry<>(3, value, Buffer::writeFloat));
    }

    public void setString(final int index, final String value) {
        this.changes.put(index, new Entry<>(4, value, Buffer::writeString));
    }

    public void setComponent(final int index, final Component value) {
        this.changes.put(index, new Entry<>(5, value, Buffer::writeComponent));
    }

    public void setBoolean(final int index, final ItemStack value) {
        this.changes.put(index, new Entry<>(7, value, Buffer::writeSlot));
    }

    public void setBoolean(final int index, final boolean value) {
        this.changes.put(index, new Entry<>(8, value, Buffer::writeBoolean));
    }

    public void setBoolean(final int index, final Position value) {
        this.changes.put(index, new Entry<>(10, value, Buffer::writePosition));
    }

    public void setNbt(final int index, final CompoundBinaryTag value) {
        this.changes.put(index, new Entry<>(10, value, Buffer::writeCompoundTag));
    }

    public void setPose(final int index, final Entity.Pose value) {
        this.changes.put(index, new Entry<>(20, value, (buffer, pose) -> buffer.writeVarInt(pose.ordinal())));
    }

    public Map<Integer, Entry<?>> changes() {
        return this.changes;
    }

    public void reset() {
        this.changes = new HashMap<>();
    }

    public record Entry<T>(int type, T t, Applier<T> applier) {

        public void write(final Buffer buffer) {
            this.applier.apply(buffer, this.t);
        }
    }

    @FunctionalInterface
    private interface Applier<T> {
        void apply(final Buffer buffer, final T t);
    }
}
