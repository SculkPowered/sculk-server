package de.bauhd.sculk.registry;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;

public class SimpleRegistry<E extends Registry.Entry> extends HashMap<String, E> implements Registry<E> {

    private final String type;
    private final E def;

    public SimpleRegistry(@NotNull String type, @NotNull E def) {
        this.type = type;
        this.def = def;
        this.register(def);
    }

    @Override
    public @NotNull String type() {
        return this.type;
    }

    @Override
    public void register(@NotNull E entry) {
        this.put(entry.name(), entry);
    }

    @Override
    public @NotNull E get(@NotNull String key) {
        return this.getOrDefault(key, this.def);
    }

    @Override
    public @NotNull Collection<E> entries() {
        return this.values();
    }

    @Override
    public @NotNull CompoundBinaryTag asNBT() {
        return CompoundBinaryTag.builder()
                .putString("type", this.type)
                .put("value", ListBinaryTag.from(this.values().stream().map(Registry.Entry::asNBT).toList()))
                .build();
    }
}
